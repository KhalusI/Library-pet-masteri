package library.library.controllers.auth;

import library.library.entities.User;
import library.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("user", new User());

        return "user/registration";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("email") String email,
                               @RequestParam("password") String password){

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        userService.save(user);

        return "redirect:/auth/login?success=true";
    }

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }
}
