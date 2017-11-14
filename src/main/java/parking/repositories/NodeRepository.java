package parking.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import parking.domain.Node;

/**
 * Created by Stanislav on 13.11.2017.
 */
@Transactional
public interface NodeRepository extends GraphRepository<Node> {

    @Query("MATCH (n:Node)-[r:EXIST_ROADS_TO]->(n1:Node) WHERE ID(n)={idFrom} AND ID(n1)={idTo} DELETE r")
    void deleteRelationFromNodeToNode(@Param("idFrom")Long idFrom, @Param("idTo") Long idTo);
}
