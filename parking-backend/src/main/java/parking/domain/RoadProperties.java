package parking.domain;

/**
 * Class for json parser
 */
public class RoadProperties {
    private Float id;
    private Float osm_id;
    private String type;
    private String name;
    private Integer tunnel;
    private Integer bridge;
    private Integer oneway;
    private String ref;
    private Float z_order;
    private String access;
    private String service;
    private String className;

    public RoadProperties(Float id, Float osm_id, String type, String name, Integer tunnel, Integer bridge, Integer oneway, String ref, Float z_order, String access, String service, String className) {
        this.id = id;
        this.osm_id = osm_id;
        this.type = type;
        this.name = name;
        this.tunnel = tunnel;
        this.bridge = bridge;
        this.oneway = oneway;
        this.ref = ref;
        this.z_order = z_order;
        this.access = access;
        this.service = service;
        this.className = className;
    }

    public Float getId() {
        return id;
    }

    public void setId(Float id) {
        this.id = id;
    }

    public Float getOsm_id() {
        return osm_id;
    }

    public void setOsm_id(Float osm_id) {
        this.osm_id = osm_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTunnel() {
        return tunnel;
    }

    public void setTunnel(Integer tunnel) {
        this.tunnel = tunnel;
    }

    public Integer getBridge() {
        return bridge;
    }

    public void setBridge(Integer bridge) {
        this.bridge = bridge;
    }

    public Integer getOneway() {
        return oneway;
    }

    public void setOneway(Integer oneway) {
        this.oneway = oneway;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Float getZ_order() {
        return z_order;
    }

    public void setZ_order(Float z_order) {
        this.z_order = z_order;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
