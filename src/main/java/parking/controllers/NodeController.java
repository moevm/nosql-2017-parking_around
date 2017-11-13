package parking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import parking.commands.NodeForm;
import parking.converters.NodeToNodeForm;
import parking.domain.Node;
import parking.services.NodeService;

import javax.validation.Valid;

/**
 * Created by Stanislav on 13.11.2017.
 */
@Controller
public class NodeController {
    private NodeService nodeService;
    private NodeToNodeForm nodeToNodeForm;

    @Autowired
    public void setNodeToNodeForm(NodeToNodeForm nodeToNodeForm){
        this.nodeToNodeForm = nodeToNodeForm;
    }

    @Autowired
    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @RequestMapping("/")
    public String redirToList(){
        return "redirect:/node/list";
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

        return "redirect:/node/show/" + savedNode.getId();
    }

    @RequestMapping("/node/delete/{id}")
    public String delete(@PathVariable String id){
        nodeService.delete(Long.valueOf(id));
        return "redirect:/node/list";
    }
}
