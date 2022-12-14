package th.ac.ku.KuPremiumRunnerWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import th.ac.ku.KuPremiumRunnerWeb.model.FDA414;
import th.ac.ku.KuPremiumRunnerWeb.service.CakesService;
import th.ac.ku.KuPremiumRunnerWeb.service.FDA414Service;
import th.ac.ku.KuPremiumRunnerWeb.service.UserService;

import java.util.UUID;

@Controller
@RequestMapping("/fda414")
public class FDA414Controller {
    @Autowired
    private FDA414Service fda414Service;
    @Autowired
    private CakesService cakesService;
    @Autowired
    private UserService userServices;

    @GetMapping("/edit/{id}")
    public String getEditForm(@PathVariable UUID id, Model model) {
        FDA414 fda414 = fda414Service.getOneById(id);
        model.addAttribute("fda414", fda414);
        return "fda414-edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute FDA414 fda414, Model model, RedirectAttributes redirectAttrs) {
        if(checkNum(fda414.getLead_s(), fda414.getLead_c())) {
            if (checkAddress(fda414.getProductName(), fda414.getR_name(), fda414.getLead_s(), fda414.getLead_c(), fda414.getLead_f())) {
//                if(checkTrueFalse(fda414.getLead_f())) {
                    fda414Service.update(fda414);
                    return "redirect:/fda414/list";
//                }else {
//                    redirectAttrs.addFlashAttribute("error", "Please type in either Pass or Not Pass!");
//                    return "redirect:/fda414/list";
//                }
            } else {
                redirectAttrs.addFlashAttribute("error", "Please fill in the rest of the information fields!");
                return "redirect:/fda414/list";
            }
        } else {
            redirectAttrs.addFlashAttribute("error", "The value must be greater than 0!");
            return "redirect:/fda414/list";
        }
    }

    @GetMapping("/list") //getAll
    public String getListForm(Model model, Authentication authentication) {
        model.addAttribute("rings8", fda414Service.getAll());
        return "fda414-list";
    }

    @GetMapping
    public String getCakes(Model model, Authentication authentication) {
        userServices.setLoginUser(authentication.getName());
        model.addAttribute("fda414", fda414Service.getAll());
        model.addAttribute("productsFDA414", cakesService.getDummy(authentication.getName()));
        return "fda414-edit";
    }

    @GetMapping("/add")
    public String getAddForm(Model model, Authentication authentication){
        model.addAttribute("fda414", fda414Service.getAll());
        model.addAttribute("productsFDA414", cakesService.getDummy(authentication.getName()));
        return "fda414-add";
    }

    @PostMapping("/add")
    public String addFDA414(@ModelAttribute FDA414 fda414, Model model, RedirectAttributes redirectAttrs) {
        // ???????????????????????????????????????????????????????????? List
        if(checkNum(fda414.getLead_s(), fda414.getLead_c())) {
            if (checkAddress(fda414.getProductName(), fda414.getR_name(), fda414.getLead_s(), fda414.getLead_c(), fda414.getLead_f())) {
                if(fda414Service.checkNameFDA(fda414.getProductName())) {
//                    if(checkTrueFalse(fda414.getLead_f())) {
                        fda414Service.addFDA414(fda414);
                        return "redirect:/fda414/list";
//                    }else {
//                        redirectAttrs.addFlashAttribute("error", "Please type in either Pass or Not Pass!");
//                        return "redirect:/fda414/add";
//                    }
                } else {
                    redirectAttrs.addFlashAttribute("error","Existed products!,please try again");
                    return "redirect:/fda414/add";
                }
            } else {
                redirectAttrs.addFlashAttribute("error", "Please fill in the rest of the information fields!");
                return "redirect:/fda414/add";
            }
        } else {
            redirectAttrs.addFlashAttribute("error", "The value must be greater than 0!");
            return "redirect:/fda414/add";
        }
    }

    public boolean checkTrueFalse(String lead_f){
        if (lead_f.equals("Pass") || lead_f.equals("Not Pass") || lead_f.equals("pass") || lead_f.equals("not pass") || lead_f.equals("NotPass")) {
            return true;
        }
        return false;
    }

    public boolean checkNum(double lead_s, double lead_c){ //Method ????????????????????????????????????????????????????????? 0
        if(lead_s >= 0 && lead_c >= 0){
            return true;
        }
        return false;
    }

    public boolean checkAddress(String productName, String r_name, double lead_s, double lead_c, String lead_f){
        if (productName.equals("") || r_name.equals("") || String.valueOf(lead_s).equals("") || String.valueOf(lead_c).equals("")
                || lead_f.equals("")){
            return false;
        }return true;
    }

    @GetMapping("/remove/{id}")
    public String removeFDA414(@PathVariable UUID id, Model model,Authentication authentication){
        FDA414 set = fda414Service.getOneById(id);
        fda414Service.delete(set);
        return "redirect:/fda414/list";
    }
}
