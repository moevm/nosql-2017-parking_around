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

    @Query("MATCH (p:Node), (p1:Node) " +
            "WHERE ID(p) = {id}  AND ID(p1) = {id1} " +
            "RETURN distance(point({ longitude: p.longitude, latitude: p.latitude, crs: 'WGS-84' }), point({ longitude: p1.longitude, latitude: p1.latitude, crs:'WGS-84' }))")
    float distanceBetweenNodes(@Param("id") Long id, @Param("id1") Long id1);

    @Query("RETURN distance(point({ longitude: {longitude1}, latitude: {latitude1}, crs: 'WGS-84' })," +
            " point({ longitude: {longitude2}, latitude: {latitude2}, crs: 'WGS-84' }))")
    float distanceBetweenPoints(@Param("longitude1") double longitude1, @Param("latitude1") double latitude1,
                                @Param("longitude2") double longitude2, @Param("latitude2") double latitude2);
}
