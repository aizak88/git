package kg.itrun.second.demo.controller;

import kg.itrun.second.demo.entity.Goods;
import kg.itrun.second.demo.repository.GoodsRepository;
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
public class GoodsController {
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/newgoods")
    public String showFormGoods(@Valid Goods goods, Model model){
        return "user/add-goods";
    }

    @PostMapping("/addgoods")
    public String addGoods(@Valid Goods goods, BindingResult result, Model model){
        if (result.hasErrors()){
            return "user/add-goods";
        }
        goodsRepository.save(goods);
        model.addAttribute("goods", goodsRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "user";
    }
    @GetMapping("/editgoods/{id}")
    public String showUpdateFormGoods(@PathVariable Integer id, Model model){
        Goods goods = goodsRepository.findOne(id);
               // .orElseThrow(()-> new IllegalArgumentException("Invalid goods id" + id));
        model.addAttribute("goods", goods);
        return "user/update-goods";
    }
    @PostMapping("/updategoods/{id}")
    public String updateGoods(@PathVariable Integer id, @Valid Goods goods, BindingResult result, Model model){
        if (result.hasErrors()){
            goods.setId(id);
            return "user/update-goods";
        }
        goodsRepository.save(goods);
        model.addAttribute("goods", goodsRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "user";
    }
    @GetMapping("/deletegoods/{id}")
    public String deleteGoods(@PathVariable Integer id, Model model){
        Goods goods = goodsRepository.findOne(id);
                //.orElseThrow(()-> new IllegalArgumentException("Ivalid user id" + id));
        goodsRepository.delete(goods);
        model.addAttribute("goods", goodsRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "user";
    }
}
