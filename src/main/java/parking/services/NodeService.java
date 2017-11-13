package parking.services;

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

    Node saveOrUpdateNodeForm(NodeForm nodeForm);
}
