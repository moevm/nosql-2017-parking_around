package parking.services;

import com.google.gson.reflect.TypeToken;
import parking.domain.Node;
import parking.domain.Road;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
