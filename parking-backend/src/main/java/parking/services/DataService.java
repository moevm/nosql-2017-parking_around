package parking.services;

import parking.domain.Node;
import parking.domain.Road;

import java.util.ArrayList;

/**
 * Service for work with map data
 */
public interface DataService {

    boolean importJSON();

    void mainImport(Double latitude, Double longitude, Double lambda);

    ArrayList<Node> getNodesAround(ArrayList<Node> nodes, Double needed_lat, Double needed_long, Double r);

    ArrayList<Road> getRoadList(String filePath);

    ArrayList<Node> getNodes(ArrayList<Road> roads);

    ArrayList<Double> getAverageCoords(ArrayList<Node> nodes);

    void setRouteRelations(ArrayList<Node> nodes, Integer oneway);
}
