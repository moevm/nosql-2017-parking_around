package parking.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import parking.commands.NodeForm;
import parking.domain.Node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

        List<Node> nodes = node.getNodes();
        if(nodes.size() != 0) {
            StringBuilder sb = new StringBuilder();
            Iterator<Node> nodeIterator = nodes.iterator();
            while (nodeIterator.hasNext()) {
                sb.append(nodeIterator.next().getId() + ",");
            }
            sb.deleteCharAt(sb.length()-1);

            nodeForm.setNodes(sb.toString());
        }
        else
            nodeForm.setNodes("");
        return nodeForm;
    }
}