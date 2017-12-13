package parking.domain;

/**
 * Main class for parser
 */
public class Road {
    private String type;
    private RoadGeometry geometry;
    private RoadProperties properties;

    public Road(String type, RoadGeometry geometry, RoadProperties properties) {
        this.type = type;
        this.geometry = geometry;
        this.properties = properties;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RoadGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(RoadGeometry geometry) {
        this.geometry = geometry;
    }

    public RoadProperties getProperties() {
        return properties;
    }

    public void setProperties(RoadProperties properties) {
        this.properties = properties;
    }
}
