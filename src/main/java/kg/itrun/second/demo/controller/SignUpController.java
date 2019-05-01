package kg.itrun.second.demo.controller;



import kg.itrun.second.demo.entity.User;
import kg.itrun.second.demo.repository.RoleRepository;
import kg.itrun.second.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class SingUpController {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping(value = "/registration")
    public String showRegistration(){
        return "registration";
    }


/*    @PostMapping(value = "/registration")
    public String signUp(@RequestBody User user){
        user.setActive(true);
        user.setRole(roleRepository.findById(1).get());
        userRepository.save(user);
        return "redirect:login";
    }*/


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        user.setPwd(bCryptPasswordEncoder.encode(user.getPwd()));
        user.setRole(roleRepository.findOne(1));
        user.setActive(true);

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userRepository.save(user);
            modelAndView.setViewName("login");

        }
        return modelAndView;
    }


}
