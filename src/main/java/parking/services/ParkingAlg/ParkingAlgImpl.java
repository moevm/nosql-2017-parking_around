package parking.services.ParkingAlg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parking.commands.ParkingForm;
import parking.domain.DistanceBtw;
import parking.domain.Node;
import parking.repositories.NodeRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stanislav on 06.12.2017.
 */
@Service
public class ParkingAlgImpl implements ParkingAlgService{

    private NodeRepository nodeRepository;

    @Autowired
    public ParkingAlgImpl(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    @Override
    public ArrayList<Node>  work(ParkingForm parkingForm){
        ArrayList<Node> validNodes = new ArrayList<>();
        Node nearestNodesToDistinationPoint = new Node();
        float minDistance = Float.MAX_VALUE;
        //Получили все вершины в бд
        ArrayList<Node> allNodes = new ArrayList<>(nodeRepository.getNodes());
        for(int i = 0; i < allNodes.size(); i++) {
            float distance = nodeRepository.distanceBetweenPoints(
                    allNodes.get(i).getLongitude(),
                    allNodes.get(i).getLatitude(),
                    parkingForm.getDistinationLongitude(),
                    parkingForm.getDistinationLatitude());
            if (distance < parkingForm.getRmax()) {
                validNodes.add(allNodes.get(i));
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestNodesToDistinationPoint = allNodes.get(i);
                }
            }
        }
        ArrayList<Node> way = new ArrayList<>();
        searchWay(validNodes, nearestNodesToDistinationPoint, way, parkingForm.getN());
        /*
        for(Node node: way)
            System.out.print(node.getName() + "->");
        */
        return way;

        //
    }

    public void searchWay(List<Node> validNodes, Node node, ArrayList<Node> answerList, int N){
        System.out.println(N);
        answerList.add(node);
        Node preNode = null;
        Node prepreNode = null;
        if(answerList.size() > 2) {
            preNode = answerList.get(answerList.size() - 2);
            prepreNode = answerList.get(answerList.size() - 3);
        }
        else if (answerList.size() == 2)
            preNode = answerList.get(answerList.size() - 2);

        ArrayList<Node> validRoads = new ArrayList<>();
        for(DistanceBtw distanceBtw: node.getNodes()){
            Node destinationNodeInPair = distanceBtw.getB();
            if(validNodes.contains(destinationNodeInPair)){
                if(!answerList.contains(destinationNodeInPair))
                    validRoads.add(destinationNodeInPair);
                else if (destinationNodeInPair != preNode && destinationNodeInPair != prepreNode){
                    if(N-- > 0)
                        validRoads.add(destinationNodeInPair);
                }
            }
        }

        if(validRoads.size() > 0) {
            float shortestWay = Float.MAX_VALUE;
            Node nearestNode = null;
            for (Node road : validRoads) {
                float distance = nodeRepository.distanceBetweenPoints(
                        node.getLongitude(),
                        node.getLatitude(),
                        road.getLongitude(),
                        road.getLatitude());
                if (distance < shortestWay) {
                    shortestWay = distance;
                    nearestNode = road;
                }
            }
            searchWay(validNodes, nearestNode, answerList, N);
        }
    }
}