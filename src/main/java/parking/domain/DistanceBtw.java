package parking.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by Stanislav on 06.12.2017.
 */
@RelationshipEntity(type="EXIST_ROADS_TO")
public class DistanceBtw {

    @GraphId
    private Long id;

    @StartNode Node A;

    @EndNode Node B;

    float distance;

    public DistanceBtw(){

    }

    public DistanceBtw(Node A, Node B){
        this.A = A;
        this.B = B;
    }

    public Long getId(){
        return id;
    }

    public Node getA() {
        return A;
    }

    public Node getB() {
        return B;
    }

    public float getDistance() {
        return distance;
    }
}
