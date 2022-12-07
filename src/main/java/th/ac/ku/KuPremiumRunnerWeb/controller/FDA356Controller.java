package th.ac.ku.KuPremiumRunnerWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import th.ac.ku.KuPremiumRunnerWeb.model.Certificate;
import th.ac.ku.KuPremiumRunnerWeb.model.FDA356;
import th.ac.ku.KuPremiumRunnerWeb.service.CakesService;
import th.ac.ku.KuPremiumRunnerWeb.service.FDA356Service;
import th.ac.ku.KuPremiumRunnerWeb.service.UserService;

import java.util.UUID;

@Controller
@RequestMapping("/fda356")
public class FDA356Controller {
    @Autowired
    private FDA356Service fda356Service;
    @Autowired
    private CakesService cakesService;
    @Autowired
    private UserService userServices;

    @GetMapping("/edit/{id}")
    public String getEditForm(@PathVariable UUID id, Model model) {
        FDA356 fda356 = fda356Service.getOneById(id);
        model.addAttribute("fda356", fda356);
        return "fda356-edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute FDA356 fda356, Model model, RedirectAttributes redirectAttrs) {
        if(checkNum(fda356.getPh_value_s(), fda356.getPh_value_c(), fda356.getColi_s(), fda356.getColi_c(), fda356.getYeast_mold_s(), fda356.getYeast_mold_r(), fda356.getYeast_c(), fda356.getMold_c())) {
            if (checkAddress(fda356.getProductName(), fda356.getR_name(), fda356.getPh_value_s(), fda356.getPh_value_c(), fda356.getPh_value_f()
                    , fda356.getColi_s(), fda356.getColi_c(), fda356.getColi_f(), fda356.getYeast_mold_s(), fda356.getYeast_mold_r()
                    , fda356.getYeast_c(), fda356.getMold_c(), fda356.getYeast_mold_f())) {
//                if(fda356Service.checkNameFDA(fda356.getProductName())) {
//                    if(checkTrueFalse(fda356.getPh_value_f(), fda356.getColi_f(), fda356.getYeast_mold_f())) {
                        fda356Service.update(fda356);
                        return "redirect:/fda356/list";
//                    }else {
//                        redirectAttrs.addFlashAttribute("error", "Please type in either Pass or Not Pass!");
//                        return "redirect:/fda356/list";
//                    }
//                } else {
//                    redirectAttrs.addFlashAttribute("error","Please don't use existing same products!");
//                    return "redirect:/fda356/add";
//                }
            } else {
                redirectAttrs.addFlashAttribute("error", "Please fill in the rest of the information fields!");
                return "redirect:/fda356/list";
            }
        } else {
            redirectAttrs.addFlashAttribute("error", "The value must be greater than 0!");
            return "redirect:/fda356/list";
        }
    }

    @GetMapping("/list") //getAll
    public String getListForm(Model model, Authentication authentication) {
        model.addAttribute("rings7", fda356Service.getAll());
        return "fda356-list";
    }

    @GetMapping
    public String getCakes(Model model, Authentication authentication) {
        userServices.setLoginUser(authentication.getName());
        model.addAttribute("fda356", fda356Service.getAll());
        model.addAttribute("productsFDA356", cakesService.getDummy(authentication.getName()));
        return "fda356-edit";
    }

    @GetMapping("/add")
    public String getAddForm(Model model, Authentication authentication){
        model.addAttribute("fda356", fda356Service.getAll());
        model.addAttribute("productsFDA356", cakesService.getDummy(authentication.getName()));
        return "fda356-add";
    }

    @PostMapping("/add")
    public String addFDA356(@ModelAttribute FDA356 fda356, Model model, RedirectAttributes redirectAttrs) {
        // พอรับเข้ามาจะเอาเข้า List
        if(checkNum(fda356.getPh_value_s(), fda356.getPh_value_c(), fda356.getColi_s(), fda356.getColi_c(), fda356.getYeast_mold_s(), fda356.getYeast_mold_r(), fda356.getYeast_c(), fda356.getMold_c())) {
            if (checkAddress(fda356.getProductName(), fda356.getR_name(), fda356.getPh_value_s(), fda356.getPh_value_c(), fda356.getPh_value_f()
                    , fda356.getColi_s(), fda356.getColi_c(), fda356.getColi_f(), fda356.getYeast_mold_s(), fda356.getYeast_mold_r()
                    , fda356.getYeast_c(), fda356.getMold_c(), fda356.getYeast_mold_f())) {
                if(fda356Service.checkNameFDA(fda356.getProductName())) {
//                    if(checkTrueFalse(fda356.getPh_value_f(), fda356.getColi_f(), fda356.getYeast_mold_f())) {
                        fda356Service.addFDA356(fda356);
                        return "redirect:/fda356/list";
//                    }else {
//                        redirectAttrs.addFlashAttribute("error", "Please type in either Pass or Not Pass!");
//                        return "redirect:/fda356/add";
//                    }
                } else {
                    redirectAttrs.addFlashAttribute("error","Existed products!,please try again");
                    return "redirect:/fda356/add";
                }
            } else {
                redirectAttrs.addFlashAttribute("error", "Please fill in the rest of the information fields!");
                return "redirect:/fda356/add";
            }
        } else {
            redirectAttrs.addFlashAttribute("error", "The value must be greater than 0!");
            return "redirect:/fda356/add";
        }
    }

    public boolean checkNum(double ph_value_s, double ph_value_c, double coli_s, double coli_c, double yeast_mold_s, double yeast_mold_r, double yeast_c, double mold_c){ //Method ดักห้ามใส่จำนวนเป็น 0
        if(ph_value_s >= 0 && ph_value_c >= 0 && coli_s >= 0 && coli_c >= 0 && yeast_mold_s >= 0 && yeast_mold_r >= 0 && yeast_c >= 0 && mold_c >= 0){
            return true;
        }
        return false;
    }

    public boolean checkAddress(String productName, String r_name, double ph_value_s, double ph_value_c, String ph_value_f
    , double coli_s, double coli_c, String coli_f, double yeast_mold_s, double yeast_mold_r, double yeast_c, double mold_c, String yeast_mold_f){
        if (productName.equals("") || r_name.equals("") || String.valueOf(ph_value_s).equals("") || String.valueOf(ph_value_c).equals("")
            || ph_value_f.equals("") || String.valueOf(coli_s).equals("") || String.valueOf(coli_c).equals("") || coli_f.equals("")
            || String.valueOf(yeast_mold_s).equals("") || String.valueOf(yeast_mold_r).equals("") || String.valueOf(yeast_c).equals("") || String.valueOf(mold_c).equals("")
            || yeast_mold_f.equals("")){
            return false;
        }return true;
    }

//    public boolean checkTrueFalse(String ph_value_f, String coli_f, String yeast_mold_f) {
//        if (ph_value_f.equals("Pass") || ph_value_f.equals("Not Pass") || ph_value_f.equals("pass") || ph_value_f.equals("not pass") || ph_value_f.equals("NotPass")) {
//            if (coli_f.equals("Pass") || coli_f.equals("Not Pass") || coli_f.equals("pass") || coli_f.equals("not pass") || coli_f.equals("NotPass")) {
//                if (yeast_mold_f.equals("Pass") || yeast_mold_f.equals("Not Pass") || yeast_mold_f.equals("pass") || yeast_mold_f.equals("not pass") || yeast_mold_f.equals("NotPass")) {
//                    return true;
//                } else {
//                    return false;
//                }
//            } else {
//                return false;
//            }
//        } else {
//            return false;
//        }
//    }

    @GetMapping("/remove/{id}")
    public String removeFDA356(@PathVariable UUID id, Model model,Authentication authentication){
        FDA356 set = fda356Service.getOneById(id);
        fda356Service.delete(set);
        return "redirect:/fda356/list";
    }
}
