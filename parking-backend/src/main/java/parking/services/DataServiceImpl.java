package parking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parking.domain.Node;
import parking.repositories.NodeRepository;

/**
 * Service for data import/export
 */

@Service
public class DataServiceImpl implements DataService {

    private NodeService nodeService;
    private NodeRepository nodeRepository;


    @Autowired
    public DataServiceImpl(NodeService service, NodeRepository repository) {
        nodeService = service;
        nodeRepository = repository;
    }


    public boolean importJSON(){

        String filePath = "git/road.json";
        nodeRepository.importFromNodes(filePath);
        return true;
    }
}
