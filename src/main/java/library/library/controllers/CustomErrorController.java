package library.library.controllers;
import library.library.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
public class CustomErrorController implements ErrorController {

    private final GroupService groupService;

//    @Autowired
    public CustomErrorController(GroupService groupService) {
        this.groupService = groupService;
    }

//    @RequestMapping("/error")
    public String handleError(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isAuthenticated = authentication != null && !authentication.getName().equals("anonymousUser");

        model.addAttribute("groups", groupService.getAll());
        model.addAttribute("isAuth", isAuthenticated);


        return "pageNotFound";
    }
}
