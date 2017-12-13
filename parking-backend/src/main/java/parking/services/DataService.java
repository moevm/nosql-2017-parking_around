package parking.services;

import parking.domain.Node;
import parking.domain.Road;

import java.util.ArrayList;

/**
 * Created by FlipBook TP300LD on 12.12.2017.
 */
public interface DataService {

    boolean importJSON();

    void mainImport();

    ArrayList<Node> getNodesAround(ArrayList<Node> nodes, Double needed_lat, Double needed_long, Double r);

    ArrayList<Road> getRoadList();

    ArrayList<Node> getNodes(ArrayList<Road> roads);

    ArrayList<Double> getAverageCoords(ArrayList<Node> nodes);
}
