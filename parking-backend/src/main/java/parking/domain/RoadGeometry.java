package parking.domain;

import java.util.ArrayList;

/**
 * Created by FlipBook TP300LD on 13.12.2017.
 */
public class RoadGeometry {
    private String type;
    private ArrayList<ArrayList<Double>> coordinates;

    public RoadGeometry(String type, ArrayList<ArrayList<Double>> coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setCoordinates(ArrayList<ArrayList<Double>> coordinates) {
        this.coordinates = coordinates;
    }

    public void setType(String type) {

        this.type = type;
    }

    public ArrayList<ArrayList<Double>> getCoordinates() {

        return coordinates;
    }
}
