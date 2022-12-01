package th.ac.ku.KuPremiumRunnerWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import th.ac.ku.KuPremiumRunnerWeb.model.Food;
import th.ac.ku.KuPremiumRunnerWeb.model.Story;
import th.ac.ku.KuPremiumRunnerWeb.service.CakesService;
import th.ac.ku.KuPremiumRunnerWeb.service.FoodService;
import th.ac.ku.KuPremiumRunnerWeb.service.StoryService;
import th.ac.ku.KuPremiumRunnerWeb.service.UserService;

import java.util.UUID;

@Controller
@RequestMapping("/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private CakesService cakesService;

    @Autowired
    private UserService userServices;

    @GetMapping("/edit/{id}")
    public String getEditForm(@PathVariable UUID id, Model model) {
        Food food = foodService.getOneById(id);
        model.addAttribute("food", food);
        return "food-edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Food food, Model model) {
        foodService.update(food);
        return "redirect:/food/list";
    }

    @GetMapping("/list") //getAll
    public String getListForm(Model model, Authentication authentication) {
        model.addAttribute("rings5", foodService.getAll());
        return "food-list";
    }

//    @GetMapping("/list/{id}") //getDummy
//    public String getListIDForm(@PathVariable UUID id, Model model, Authentication authentication) {
//        Story rings = storyService.getOneById(id);
//        model.addAttribute("rings4", storyService.getDummy(authentication.getName() ,rings.getProductName()));
//        return "story-list";
//    }

    @GetMapping
    public String getCakes(Model model, Authentication authentication) {
        userServices.setLoginUserCakes(authentication.getName());
        model.addAttribute("food", foodService.getAll());
        return "food-edit";
    }

    @GetMapping("/add")
    public String getAddForm(Model model){
        model.addAttribute("food", foodService.getAll());
        return "food-add";
    }

    @PostMapping("/add")
    public String addFood(@ModelAttribute Food food, Model model, RedirectAttributes redirectAttrs) {
        // พอรับเข้ามาจะเอาเข้า List
        if(checkAddress(food.getProdFoodName(), food.getProductName())) {
            foodService.addStory(food);
            return "redirect:/food/list";
        }
        else {
            redirectAttrs.addFlashAttribute("error","Please fill all the information fields!");
            return "redirect:/food/add";
        }
    }

    public boolean checkAddress(String prodFoodName, String productName){
        if (prodFoodName.equals("") || (productName.equals(""))){
            return false;
        }return true;
    }
}
