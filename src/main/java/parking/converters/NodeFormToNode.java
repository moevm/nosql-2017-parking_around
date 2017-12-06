package parking.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import parking.commands.NodeForm;
import parking.domain.DistanceBtw;
import parking.domain.Node;
import parking.repositories.NodeRepository;
import java.util.ArrayList;

/**
 * Created by Stanislav on 13.11.2017.
 */
@Component
public class NodeFormToNode implements Converter<NodeForm, Node> {
    NodeRepository nodeRepository;

    @Autowired
    public void setNodeRepository(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }


    @Override
    public Node convert(NodeForm nodeForm) {
        Node node = new Node();
        if (nodeForm.getId() != null  && !StringUtils.isEmpty(nodeForm.getId())) {
            node.setId(new Long(nodeForm.getId()));
        }
        node.setName(nodeForm.getName());
        node.setLongitude(nodeForm.getLongitude());
        node.setLatitude(nodeForm.getLatitude());
        if(!nodeForm.getNodes().isEmpty()) {
            String[] ids = nodeForm.getNodes().split(",");
            ArrayList<DistanceBtw> distancesBtw = new ArrayList<>();

            for (String id : ids) {
                Node direction = nodeRepository.findOne(Long.parseLong(id));
                float distance = nodeRepository.distanceBetweenPoints(nodeForm.getLongitude(), nodeForm.getLatitude(),
                        direction.getLongitude(), direction.getLatitude());
                distancesBtw.add(new DistanceBtw(node, direction, distance));
            }
            node.setDistanceBtws(distancesBtw);
        }

        return node;
    }
}
