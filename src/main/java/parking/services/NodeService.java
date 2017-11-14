package parking.services;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.transaction.annotation.Transactional;
import parking.commands.NodeForm;
import parking.domain.Node;

import java.util.List;

/**
 * Created by Stanislav on 13.11.2017.
 */
public interface NodeService {
    List<Node> listAll();

    Node getById(Long id);

    Node saveOrUpdate(Node node);

    void delete(Long id);

    void deleteRelationFromNodeToNode(Long idFrom, Long idTo);

    float distanceBetweenNodes(Long id, Long id1);

    Node saveOrUpdateNodeForm(NodeForm nodeForm);
}
