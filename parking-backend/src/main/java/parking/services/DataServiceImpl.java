package parking.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parking.domain.DistanceBtw;
import parking.domain.Node;
import parking.domain.Road;
import parking.repositories.NodeRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for data import/export
 */

@Service
@SuppressWarnings("Duplicates")
public class DataServiceImpl implements DataService {

    private NodeService nodeService;
    private NodeRepository nodeRepository;
    private static final Type ROAD_TYPE =
            new TypeToken<List<Road>>() {
            }.getType();


    @Autowired
    public DataServiceImpl(NodeService service, NodeRepository repository) {
        nodeService = service;
        nodeRepository = repository;
    }


    public boolean importJSON() {
//        String filePath = "git/road1.json";
//        nodeRepository.importFromNodes(filePath);
        return true;
    }

    public void mainImport() {
        String filePath = "C:\\git\\road.json";
        ArrayList<Road> roads = getRoadList(filePath);
        ArrayList<Node> nodes = getNodes(roads);

        ArrayList<Double> averageCoords = getAverageCoords(nodes);
        Double average_lat = averageCoords.get(0);
        Double average_long = averageCoords.get(1);

        // SPBETU coordinates
        ArrayList<Node> letiNodes = getNodesAround(nodes, 59.58179, 30.19250, 0.1);

        for (Node n : letiNodes
                ) {
            System.out.println(letiNodes.size());
            nodeService.saveOrUpdate(n);
        }
        nodeRepository.createCrossRoadsRelations(); // соединяем перекрестки
        System.out.println("Check1");
    }

    public ArrayList<Node> getNodesAround(ArrayList<Node> nodes, Double needed_lat, Double needed_long, Double r) {
        ArrayList<Node> nodesAround = new ArrayList<>();
        for (Node n : nodes
                ) {
            if (Math.abs(n.getLatitude() - needed_lat) < r && Math.abs(n.getLongitude() - needed_long) < r) {
                nodesAround.add(n);
            } else {
                System.out.println(n.getName() + " не подходит");
            }

        }
        System.out.println("Подошло " + nodesAround.size() + " вершин");
        return nodesAround;
    }

    public ArrayList<Double> getAverageCoords(ArrayList<Node> nodes) {
        Double average_lat = 0.0, average_long = 0.0;
        for (Node n : nodes
                ) {
            average_lat += n.getLatitude();
            average_long += n.getLongitude();
        }
        average_lat = average_lat / nodes.size();
        average_long = average_long / nodes.size();
        ArrayList<Double> coords = new ArrayList<>();
        coords.add(average_lat);
        coords.add(average_long);
        return coords;
    }

    public ArrayList<Node> getNodes(ArrayList<Road> roads) {
        ArrayList<Node> nodes = new ArrayList<>();
        for (Road r : roads) {
            for (ArrayList<Double> c : r.getGeometry().getCoordinates()) {
                nodes.add(new Node(r.getProperties().getId().longValue(),
                        r.getProperties().getName(), c.get(1), c.get(0)));
            }
            setRouteRelations(nodes, r.getProperties().getOneway()); // slow with it
        }
        return nodes;
    }

    public ArrayList<Road> getRoadList(String filePath) {
        File file = new File(filePath);
        ArrayList<Road> data = new ArrayList<>();
        if (file.canRead()) {
            Gson gson = new Gson();
            JsonReader reader = null;
            try {
                reader = new JsonReader(new FileReader(file));
                data = gson.fromJson(reader, ROAD_TYPE);
                System.out.println(data.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Can't read file");
        }
        ArrayList<Road> notFootway = new ArrayList<>();

        for (Road r : data) {
            if (!r.getProperties().getType().equals("footway")) {
                notFootway.add(r);
            }
        }
        return notFootway;
    }

    public DistanceBtw getDistance(Node a, Node b) {
        float distance = nodeRepository.distanceBetweenPoints(a.getLongitude(), a.getLatitude(),
                b.getLongitude(), b.getLatitude());
        return new DistanceBtw(a, b, distance);
    }

    public void setRouteRelations(ArrayList<Node> nodes, Integer oneway) {
        if (nodes.size() > 0) {
            for (int i = 0; i < nodes.size() - 1; i++) {
                for (int j = 1; j < nodes.size(); j++) {
                    Node a = nodes.get(i), b = nodes.get(j);
                    if (a.getRouteId().equals(b.getRouteId())) {
                        a.addDistanceBtw(getDistance(a, b));
                        if (oneway == 0) {
                            b.addDistanceBtw(getDistance(b, a));
                        }
                    }
                }
            }
        }
    }
}
