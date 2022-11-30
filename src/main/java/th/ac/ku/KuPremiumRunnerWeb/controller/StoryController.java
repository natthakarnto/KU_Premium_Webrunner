package th.ac.ku.KuPremiumRunnerWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import th.ac.ku.KuPremiumRunnerWeb.model.Story;
import th.ac.ku.KuPremiumRunnerWeb.service.CakesService;
import th.ac.ku.KuPremiumRunnerWeb.service.StoryService;
import th.ac.ku.KuPremiumRunnerWeb.service.UserService;

import java.util.UUID;

@Controller
@RequestMapping("/story")
public class StoryController {
    @Autowired
    private StoryService storyService;

    @Autowired
    private CakesService cakesService;

    @Autowired
    private UserService userServices;

    @GetMapping("/edit/{id}")
    public String getEditForm(@PathVariable UUID id, Model model) {
        Story story = storyService.getOneById(id);
        model.addAttribute("story", story);
        return "story-edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Story story, Model model) {
        storyService.update(story);
        return "redirect:/story/list";
    }

    @GetMapping("/list") //getAll
    public String getListForm(Model model, Authentication authentication) {
        model.addAttribute("rings4", storyService.getAll());
        return "story-list";
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
        model.addAttribute("story", storyService.getAll());
        return "story-edit";
    }

    @GetMapping("/add")
    public String getAddForm(Model model){
        model.addAttribute("story", storyService.getAll());
        return "story-add";
    }

    @PostMapping("/add")
    public String addStory(@ModelAttribute Story story, Model model, RedirectAttributes redirectAttrs) {
        // พอรับเข้ามาจะเอาเข้า List
        if(checkAddress(story.getProdStoryName(), story.getProductName())) {
            storyService.addStory(story);
            return "redirect:/story/list";
        }
        else {
            redirectAttrs.addFlashAttribute("error","Please fill all the information fields!");
            return "redirect:/story/add";
        }
    }

    public boolean checkAddress(String prodStoryName, String productName){
        if (prodStoryName.equals("") || (productName.equals(""))){
            return false;
        }return true;
    }
}
