package th.ac.ku.KuPremiumRunnerWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import th.ac.ku.KuPremiumRunnerWeb.model.Certificate;
import th.ac.ku.KuPremiumRunnerWeb.model.Research;
import th.ac.ku.KuPremiumRunnerWeb.service.CakesService;
import th.ac.ku.KuPremiumRunnerWeb.service.ResearchService;
import th.ac.ku.KuPremiumRunnerWeb.service.UserService;

import java.util.UUID;

@Controller
@RequestMapping("/research")
public class ResearchController {

    @Autowired
    private ResearchService researchService;

    @Autowired
    private CakesService cakesService;

    @Autowired
    private UserService userServices;

    @GetMapping("/edit/{id}")
    public String getEditForm(@PathVariable UUID id, Model model) {
        Research research = researchService.getOneById(id);
        model.addAttribute("research", research);
        return "research-edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Research research, Model model, RedirectAttributes redirectAttrs) {
        // พอรับเข้ามาจะเอาเข้า List
        if(checkAddress(research.getRelatedResearchName(), research.getProductName())) {
            researchService.update(research);
            return "redirect:/research/list";
        }
        else {
            redirectAttrs.addFlashAttribute("error","Please fill all the information fields!");
            return "redirect:/research/list";
        }
    }

    @GetMapping("/list") //getAll
    public String getListForm(Model model, Authentication authentication) {
        //model.addAttribute("rings", rings);
        //model.addAttribute("rings2", ringsService.getDummy(authentication.getName(),rings.getName()));
        model.addAttribute("rings3", researchService.getAll());
        return "research-list";
    }

    @GetMapping
    public String getCakes(Model model, Authentication authentication) {
        userServices.setLoginUserCakes(authentication.getName());
        model.addAttribute("research", researchService.getAll());
        return "research-edit";
    }

    @GetMapping("/add")
    public String getAddForm(Model model){
        model.addAttribute("research", researchService.getAll());
        return "research-add";
    }

    @PostMapping("/add")
    public String addResearch(@ModelAttribute Research research, Model model, RedirectAttributes redirectAttrs) {
        // พอรับเข้ามาจะเอาเข้า List

        if(checkAddress(research.getRelatedResearchName(), research.getProductName())) {
            researchService.addResearch(research);
            return "redirect:/research/list";
        }
        else {
            redirectAttrs.addFlashAttribute("error","Please fill all the information fields!");
            return "redirect:/research/add";
        }
    }

    public boolean checkAddress(String relatedResearchName, String productName){
        if (relatedResearchName.equals("") || (productName.equals(""))){
            return false;
        }return true;
    }

    @GetMapping("/remove/{id}")
    public String removeResearch(@PathVariable UUID id, Model model,Authentication authentication){
        Research set = researchService.getOneById(id);
        researchService.delete(set);
        return "redirect:/research/list";
    }
}
