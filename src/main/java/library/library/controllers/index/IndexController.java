package library.library.controllers.index;

import library.library.entities.Group;
import library.library.repositories.GroupRepo;
import library.library.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    private final GroupService groupService;

    @Autowired
    public IndexController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public String index(){
        return "index/index";
    }

    @GetMapping("/groups/{id}")
    public String groups(@PathVariable Long id, Model model){
        Group group = groupService.getById(id);


        model.addAttribute("group", group);

        return "groups/group";
    }

}
