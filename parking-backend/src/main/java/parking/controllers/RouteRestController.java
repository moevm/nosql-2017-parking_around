//package parking.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import parking.commands.NodeForm;
//import parking.commands.ParkingForm;
//import parking.converters.NodeToNodeForm;
//import parking.domain.Node;
//import parking.services.ParkingAlg.ParkingAlgService;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/route")
//public class RouteRestController {
//
//    private ParkingAlgService parkingAlgService;
//
//    private NodeToNodeForm nodeToNodeForm;
//
//    @Autowired
//    public void setParkingAlgService(ParkingAlgService parkingAlgService) {
//        this.parkingAlgService = parkingAlgService;
//    }
//
//    @Autowired
//    public void setNodeToNodeForm(NodeToNodeForm nodeToNodeForm) {
//        this.nodeToNodeForm = nodeToNodeForm;
//    }
//
//    @RequestMapping(value = "/build/{radius}", method = RequestMethod.GET)
//    public List<NodeForm> buildRoute(@PathVariable("radius") Long radius, @RequestParam("latitude") Float latitude,
//                                        @RequestParam("longitude") Float longitude) {
//        ParkingForm parkingForm = new ParkingForm();
//        parkingForm.setDestinationLatitude(latitude);
//        parkingForm.setDestinationLongitude(longitude);
//        parkingForm.setRmax(radius);
//        parkingForm.setN(5);
//        List<Node> way = parkingAlgService.work(parkingForm);
//        return nodeToNodeForm.convertList(way);
//    }
//}
