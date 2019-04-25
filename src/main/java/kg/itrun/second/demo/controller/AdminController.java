package kg.itrun.second.demo.controller;

import kg.itrun.second.demo.entity.User;
import kg.itrun.second.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AdminController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/signup")
    public String showSignUpForm(User user, Model model){
         model.addAttribute("users", userRepository.findAll());
        return "admin/add-user";
    }
    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model){
        if (result.hasErrors()){
            return "add-user";
        }

        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "admin";
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model){
        User user = userRepository.findOne(id);
                //.orElseThrow(()->new  IllegalArgumentException("Invalid user id" + id));
        model.addAttribute("user", user);

        return "admin/update-user";
    }
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @Valid User user, BindingResult result, Model model){
        if (result.hasErrors()){
            user.setId(id);
            return "update-user";
        }
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        //model.addAttribute("goods", goodsRepository.findAll());
        return "admin";
    }
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, Model model){
        User user = userRepository.findOne(id);
                //.orElseThrow(()->new IllegalArgumentException("Invalid user id: "+ id ));

        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());
        //.addAttribute("goods", goodsRepository.findAll());
        return "admin";
    }

}
