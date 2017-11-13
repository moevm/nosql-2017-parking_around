package parking.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import parking.commands.NodeForm;
import parking.domain.Node;

/**
 * Created by Stanislav on 13.11.2017.
 */
@Component
public class NodeFormToNode implements Converter<NodeForm, Node> {

    @Override
    public Node convert(NodeForm nodeForm) {
        Node node = new Node();
        if (nodeForm.getId() != null  && !StringUtils.isEmpty(nodeForm.getId())) {
            node.setId(new Long(nodeForm.getId()));
        }
        node.setName(nodeForm.getName());
        node.setLongitude(nodeForm.getLongitude());
        node.setLatitude(nodeForm.getLatitude());
        return node;
    }
}
