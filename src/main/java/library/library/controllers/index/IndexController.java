package library.library.controllers.index;

import library.library.entities.User;
import library.library.services.GroupService;
import library.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final GroupService groupService;
    private final UserService userService;

    private User currentUser;

    @Autowired
    public IndexController(GroupService groupService, UserService userService) {
        this.groupService = groupService;
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isAuthenticated = authentication != null && !authentication.getName().equals("anonymousUser");

        model.addAttribute("groups", groupService.getAll());
        model.addAttribute("isAuth", isAuthenticated);

        return "index/index";
    }

    @GetMapping("/auth/logout")
    public String logoutGet(){
        return "user/logout";
    }

}
