package library.library.controllers.index;

import library.library.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isAuthenticated = authentication != null && !authentication.getName().equals("anonymousUser");

        model.addAttribute("groups", groupService.getAll());
        model.addAttribute("isAuth", isAuthenticated);

        return "index/index";
    }

}
