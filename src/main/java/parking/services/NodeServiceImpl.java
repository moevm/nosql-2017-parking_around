package parking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import parking.commands.NodeForm;
import parking.converters.NodeFormToNode;
import parking.converters.NodeToNodeForm;
import parking.domain.Node;
import parking.repositories.NodeRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stanislav on 13.11.2017.
 */
@Service
public class NodeServiceImpl implements NodeService {

    private NodeRepository nodeRepository;
    private NodeFormToNode nodeFormToNode;

    @Autowired
    public NodeServiceImpl(NodeRepository nodeRepository, NodeFormToNode nodeFormToNode) {
        this.nodeRepository = nodeRepository;
        this.nodeFormToNode = nodeFormToNode;
    }


    @Override
    public List<Node> listAll() {
        List<Node> nodes = new ArrayList<>();
        nodeRepository.findAll().forEach(nodes::add); //fun with Java 8
        return nodes;
    }

    @Override
    public Node getById(Long id) {
        return nodeRepository.findOne(id);
    }

    @Override
    public Node saveOrUpdate(Node node) {
        nodeRepository.save(node);
        return node;
    }

    @Override
    public void delete(Long id) {
        nodeRepository.delete(id);

    }

    @Override
    public void deleteRelationFromNodeToNode(Long idFrom, Long idTo){
        nodeRepository.deleteRelationFromNodeToNode(idFrom, idTo);
    }

    @Override
    public Node saveOrUpdateNodeForm(NodeForm nodeForm) {
        Node savedNode = saveOrUpdate(nodeFormToNode.convert(nodeForm));
        System.out.println("Saved Node Id: " + savedNode.getId());
        return savedNode;
    }
}
