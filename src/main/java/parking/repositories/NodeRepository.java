package parking.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;
import parking.domain.Node;

/**
 * Created by Stanislav on 13.11.2017.
 */
public interface NodeRepository extends GraphRepository<Node>{
}
