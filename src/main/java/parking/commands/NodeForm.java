package parking.commands;

import parking.domain.Node;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stanislav on 13.11.2017.
 */
public class NodeForm {
    private Long id;
    private String name;
    private double latitude;
    private double longitude;
    private String nodes;

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

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }
}
