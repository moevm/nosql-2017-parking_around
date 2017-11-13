package parking.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import parking.commands.NodeForm;
import parking.domain.Node;

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
        return nodeForm;
    }
}