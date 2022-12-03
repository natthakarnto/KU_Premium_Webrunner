package th.ac.ku.KuPremiumRunnerWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import th.ac.ku.KuPremiumRunnerWeb.model.FDA414;
import th.ac.ku.KuPremiumRunnerWeb.model.FDA416;
import th.ac.ku.KuPremiumRunnerWeb.service.CakesService;
import th.ac.ku.KuPremiumRunnerWeb.service.FDA414Service;
import th.ac.ku.KuPremiumRunnerWeb.service.FDA416Service;
import th.ac.ku.KuPremiumRunnerWeb.service.UserService;

import java.util.UUID;

@Controller
@RequestMapping("/fda416")
public class FDA416Controller {
    @Autowired
    private FDA416Service fda416Service;
    @Autowired
    private CakesService cakesService;
    @Autowired
    private UserService userServices;

    @GetMapping("/edit/{id}")
    public String getEditForm(@PathVariable UUID id, Model model) {
        FDA416 fda416 = fda416Service.getOneById(id);
        model.addAttribute("fda416", fda416);
        return "fda416-edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute FDA416 fda416, Model model, RedirectAttributes redirectAttrs) {
        if(checkNum(fda416.getSalla_s(), fda416.getSalla_c(), fda416.getSareus_s(), fda416.getSareus_c())) {
            if (checkAddress(fda416.getProductName(), fda416.getR_name(), fda416.getSalla_s(), fda416.getSalla_c()
                    , fda416.getSalla_f(), fda416.getSareus_c(), fda416.getSareus_c(), fda416.getSareus_f())) {
//                if(fda416Service.checkNameFDA(fda416.getProductName())) {
                    fda416Service.addFDA416(fda416);
                    return "redirect:/fda416/list";
//                } else {
//                    redirectAttrs.addFlashAttribute("error","Please don't use existing same products!");
//                    return "redirect:/fda416/add";
//                }
            } else {
                redirectAttrs.addFlashAttribute("error", "Please fill all the information fields!");
                return "redirect:/fda416/edit";
            }
        } else {
            redirectAttrs.addFlashAttribute("error", "negative number is not allowed!");
            return "redirect:/fda416/edit";
        }
    }

    @GetMapping("/list") //getAll
    public String getListForm(Model model, Authentication authentication) {
        model.addAttribute("rings9", fda416Service.getAll());
        return "fda416-list";
    }

    @GetMapping
    public String getCakes(Model model, Authentication authentication) {
        userServices.setLoginUser(authentication.getName());
        model.addAttribute("fda416", fda416Service.getAll());
        return "fda416-edit";
    }

    @GetMapping("/add")
    public String getAddForm(Model model){
        model.addAttribute("fda416", fda416Service.getAll());
        return "fda416-add";
    }

    @PostMapping("/add")
    public String addFDA416(@ModelAttribute FDA416 fda416, Model model, RedirectAttributes redirectAttrs) {
        // พอรับเข้ามาจะเอาเข้า List
        if(checkNum(fda416.getSalla_s(), fda416.getSalla_c(), fda416.getSareus_s(), fda416.getSareus_c())) {
            if (checkAddress(fda416.getProductName(), fda416.getR_name(), fda416.getSalla_s(), fda416.getSalla_c()
                    , fda416.getSalla_f(), fda416.getSareus_c(), fda416.getSareus_c(), fda416.getSareus_f())) {
                fda416Service.addFDA416(fda416);
                return "redirect:/fda416/list";
            } else {
                redirectAttrs.addFlashAttribute("error", "Please fill all the information fields!");
                return "redirect:/fda416/add";
            }
        } else {
            redirectAttrs.addFlashAttribute("error", "negative number is not allowed!");
            return "redirect:/fda416/edit";
        }
    }

    public boolean checkNum(double lead_s, double lead_c, double sareus_s, double sareus_c){ //Method ดักห้ามใส่จำนวนเป็น 0
        if(lead_s >= 0 && lead_c >= 0 && sareus_s >= 0 && sareus_c >= 0){
            return true;
        }
        return false;
    }

    public boolean checkAddress(String productName, String r_name, double lead_s, double lead_c, String lead_f, double sareus_s, double sareus_c, String sareus_f){
        if (productName.equals("") || r_name.equals("") || String.valueOf(lead_s).equals("") || String.valueOf(lead_c).equals("")
                || lead_f.equals("") || String.valueOf(sareus_s).equals("") || String.valueOf(sareus_c).equals("") || sareus_f.equals("")){
            return false;
        }return true;
    }

    @GetMapping("/remove/{id}")
    public String removeFDA416(@PathVariable UUID id, Model model,Authentication authentication){
        FDA416 set = fda416Service.getOneById(id);
        fda416Service.delete(set);
        return "redirect:/fda416/list";
    }
}
