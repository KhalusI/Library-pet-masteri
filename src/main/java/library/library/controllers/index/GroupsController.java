package library.library.controllers.index;

import library.library.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/groups")
public class GroupsController {
    private final GroupService groupService;

    @Autowired
    public GroupsController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public String groupsIndex(Model model){
        model.addAttribute("groups", groupService.getAll());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isAuthenticated = authentication != null && !authentication.getName().equals("anonymousUser");

        if(!isAuthenticated){
            return "redirect:auth/login";
        }

        return "groups/index";
    }

    @GetMapping("/{id}")
    public String group(@PathVariable Long id, Model model){
        model.addAttribute("group", groupService.getById(id));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isAuthenticated = authentication != null && !authentication.getName().equals("anonymousUser");

        if(!isAuthenticated){
            return "redirect:auth/login";
        }

        return "groups/group";
    }
}













