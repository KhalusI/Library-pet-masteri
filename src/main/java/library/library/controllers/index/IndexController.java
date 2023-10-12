package library.library.controllers.index;

import library.library.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final GroupService groupService;

    @Autowired
    public IndexController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("groups", groupService.getAll());

        return "index/index";
    }

}
