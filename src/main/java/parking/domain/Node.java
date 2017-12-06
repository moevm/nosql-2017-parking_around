package parking.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.Index;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jt on 1/10/17.
 */
@NodeEntity
public class Node {

    @GraphId
    private Long id;

    @Index(unique=true)
    private String name;
    private double latitude;
    private double longitude;

    @Override
    public String toString() {
        return id.toString();
    }

    @Relationship(type = "EXIST_ROADS_TO", direction = Relationship.OUTGOING)
    private List<DistanceBtw> distanceBtws = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<DistanceBtw> getNodes() {
        return distanceBtws;
    }

    public void addDistanceBtw(DistanceBtw distanceBtw){
        this.distanceBtws.add(distanceBtw);
    }

    public void setDistanceBtws(List<DistanceBtw> distanceBtws) {
        this.distanceBtws = distanceBtws;
    }

    public List<DistanceBtw> getDistanceBtws() {
        return distanceBtws;
    }

    /*
    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
    */

}
