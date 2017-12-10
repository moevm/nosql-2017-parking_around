package parking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import parking.commands.DistanceForm;
import parking.commands.NodeForm;
import parking.commands.ParkingForm;
import parking.converters.NodeToDistanceForm;
import parking.converters.NodeToNodeForm;
import parking.domain.Node;
import parking.services.NodeService;
import parking.services.ParkingAlg.ParkingAlgService;
import javax.validation.Valid;
import java.util.ArrayList;

/**
 * Created by Stanislav on 13.11.2017.
 */
@Controller
public class NodeController {
    private NodeService nodeService;
    private NodeToNodeForm nodeToNodeForm;
    private NodeToDistanceForm nodeToDistanceForm;
    private ParkingAlgService parkingAlgService;

    @Autowired
    public void setNodeToNodeForm(NodeToNodeForm nodeToNodeForm){
        this.nodeToNodeForm = nodeToNodeForm;
    }

    @Autowired
    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @Autowired
    public void setNodeToDistanceForm(NodeToDistanceForm nodeToDistanceForm) {
        this.nodeToDistanceForm = nodeToDistanceForm;
    }

    @Autowired
    public void setParkingAlgService(ParkingAlgService parkingAlgService) {
        this.parkingAlgService = parkingAlgService;
    }

    @RequestMapping("/")
    public String redirToList(){
        return "redirect:/node/list";
    }

    @RequestMapping("/node/parking_around")
    public String redirToParking(Model model){
        model.addAttribute("ParkingForm", new ParkingForm());
        return "node/parking_around";
    }

    @RequestMapping(value = "/parkingAlg", method = RequestMethod.POST)
    public String parkingAlg(Model model, @Valid ParkingForm parkingForm ){
        ArrayList<Node> way = parkingAlgService.work(parkingForm);
        StringBuilder sb = new StringBuilder();
        for(Node node: way)
            sb.append(node.getName() + "->");
        sb.append("end");
        parkingForm.setAnswer(sb.toString());
        model.addAttribute("ParkingForm", parkingForm);
        return "node/parking_around";
    }

    @RequestMapping({"/node/list", "/node"})
    public String listNodes(Model model){
        model.addAttribute("nodes", nodeService.listAll());
        return "node/list";
    }

    @RequestMapping("/node/show/{id}")
    public String getNode(@PathVariable String id, Model model){
        model.addAttribute("node", nodeService.getById(Long.valueOf(id)));
        return "node/show";
    }

    @RequestMapping("node/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        Node node = nodeService.getById(Long.valueOf(id));
        NodeForm nodeForm = nodeToNodeForm.convert(node);

        model.addAttribute("nodeForm", nodeForm);
        return "node/nodeform";
    }

    @RequestMapping("/node/new")
    public String newNode(Model model){
        model.addAttribute("nodeForm", new NodeForm());
        return "node/nodeform";
    }

    @RequestMapping(value = "/node", method = RequestMethod.POST)
    public String saveOrUpdateNode(@Valid NodeForm nodeForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "node/nodeform";
        }

        Node savedNode = nodeService.saveOrUpdateNodeForm(nodeForm);
        /*
        if(nodeForm.getId() != null) {
            Node nodeInDB = nodeService.getById(nodeForm.getId());
            if (nodeInDB != null && nodeInDB.getNodes().size() > savedNode.getNodes().size()) {
                Iterator<Node> iterator = nodeInDB.getNodes().iterator();
                while (iterator.hasNext()) {
                    Node buf = iterator.next();
                    if (!savedNode.getNodes().contains(buf))
                        nodeService.deleteRelationFromNodeToNode(nodeInDB.getId(), buf.getId());
                }
            }
        }
        */
        return "redirect:/node/show/" + savedNode.getId();
    }

    @RequestMapping("/node/delete/{id}")
    public String delete(@PathVariable String id){
        nodeService.delete(Long.valueOf(id));
        return "redirect:/node/list";
    }

    @RequestMapping("/node/distance/{id}")
    public String distance(@PathVariable String id, Model model){
        model.addAttribute("nodes", nodeService.listAll());
        DistanceForm distanceForm = nodeToDistanceForm.convert(nodeService.getById(Long.valueOf(id)));
        model.addAttribute("distanceForm", distanceForm);
        return "node/distance";
    }

    @RequestMapping(value = "/distance", method = RequestMethod.POST)
    public String calculateDistance(DistanceForm distanceForm, Model model){
        float distance = nodeService.distanceBetweenNodes(distanceForm.getIdFrom(), distanceForm.getIdTo());
        distanceForm.setDistance(distance);
        distanceForm.setNameTo(nodeService.getById(distanceForm.getIdTo()).getName());
        model.addAttribute("nodes", nodeService.listAll());
        model.addAttribute("distanceForm", distanceForm);
        return "node/distance";
    }

}

