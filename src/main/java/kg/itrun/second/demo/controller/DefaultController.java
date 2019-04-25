package kg.itrun.second.demo.controller;



import kg.itrun.second.demo.repository.GoodsRepository;
import kg.itrun.second.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GoodsRepository goodsRepository;

    @GetMapping("/")
    public String home1() {
        return "/home";
    }

    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "/admin";
    }

    @GetMapping("/user")
    public String user(Model model) {
        model.addAttribute("goods", goodsRepository.findAll());
        return "/user";
    }

    @GetMapping("/about")
    public String about() {
        return "/about";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

}