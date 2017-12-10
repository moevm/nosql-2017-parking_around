package parking.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import parking.commands.NodeForm;
import parking.domain.DistanceBtw;
import parking.domain.Node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Stanislav on 13.11.2017.
 */
@Component
public class NodeToNodeForm implements Converter<Node, NodeForm> {
    @Override
    public NodeForm convert(Node node){
        NodeForm nodeForm = new NodeForm();
        nodeForm.setId(node.getId());
        nodeForm.setName(node.getName());
        nodeForm.setLatitude(node.getLatitude());
        nodeForm.setLongitude(node.getLongitude());

        List<DistanceBtw> distanceBtws = node.getDistanceBtws();
        if(distanceBtws.size() != 0) {
            StringBuilder sb = new StringBuilder();
            Iterator<DistanceBtw> distanceBtwsIterator = distanceBtws.iterator();
            while (distanceBtwsIterator.hasNext()) {
                sb.append(distanceBtwsIterator.next().getB().getId() + ",");
            }
            sb.deleteCharAt(sb.length()-1);

            nodeForm.setNodes(sb.toString());
        }
        else
            nodeForm.setNodes("");
        return nodeForm;
    }

    public List<NodeForm> convertList(List<Node> nodeList) {
        return nodeList.stream().map(this::convert)
                .collect(Collectors.toList());
    }
}