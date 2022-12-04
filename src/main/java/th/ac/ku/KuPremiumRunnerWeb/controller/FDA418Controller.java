package th.ac.ku.KuPremiumRunnerWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import th.ac.ku.KuPremiumRunnerWeb.model.FDA416;
import th.ac.ku.KuPremiumRunnerWeb.model.FDA418;
import th.ac.ku.KuPremiumRunnerWeb.service.CakesService;
import th.ac.ku.KuPremiumRunnerWeb.service.FDA418Service;
import th.ac.ku.KuPremiumRunnerWeb.service.UserService;

import java.util.UUID;

@Controller
@RequestMapping("/fda418")
public class FDA418Controller {
    @Autowired
    private FDA418Service fda418Service;
    @Autowired
    private CakesService cakesService;
    @Autowired
    private UserService userServices;

    @GetMapping("/edit/{id}")
    public String getEditForm(@PathVariable UUID id, Model model) {
        FDA418 fda418 = fda418Service.getOneById(id);
        model.addAttribute("fda418", fda418);
        return "fda418-edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute FDA418 fda418, Model model, RedirectAttributes redirectAttrs) {
        if(checkNum(fda418.getSum_benzoic_sorbic(), fda418.getBenzoic_c(), fda418.getSorbic_c(), fda418.getSynt_s(), fda418.getSynt_c()
                , fda418.getSod_s(), fda418.getSod_c(), fda418.getPotas_s(), fda418.getPotas_c(), fda418.getPlate_s(), fda418.getPlate_c())) {
            if (checkAddress(fda418.getProductName(), fda418.getR_name(), fda418.getSum_benzoic_sorbic(), fda418.getBenzoic_c()
                    , fda418.getSorbic_c(), fda418.getBenzoic_sorbic_f(), fda418.getSynt_s(), fda418.getSynt_c(), fda418.getSynt_f()
                    , fda418.getSod_s(), fda418.getSod_c(), fda418.getSod_f(), fda418.getPotas_s(), fda418.getPotas_c(), fda418.getPotas_f()
                    , fda418.getPlate_s(), fda418.getPlate_c(), fda418.getPlate_f())) {
//                if(fda418Service.checkNameFDA(fda418.getProductName())) {
                    fda418Service.addFDA418(fda418);
                    return "redirect:/fda418/list";
//                } else {
//                    redirectAttrs.addFlashAttribute("error","Please don't use existing same products!");
//                    return "redirect:/fda418/add";
//                }
            } else {
                redirectAttrs.addFlashAttribute("error", "Please fill all the information fields!");
                return "redirect:/fda418/list";
            }
        } else {
            redirectAttrs.addFlashAttribute("error", "negative number is not allowed!");
            return "redirect:/fda418/list";
        }
    }

    @GetMapping("/list") //getAll
    public String getListForm(Model model, Authentication authentication) {
        model.addAttribute("rings10", fda418Service.getAll());
        return "fda418-list";
    }

    @GetMapping
    public String getCakes(Model model, Authentication authentication) {
        userServices.setLoginUser(authentication.getName());
        model.addAttribute("fda418", fda418Service.getAll());
        return "fda418-edit";
    }

    @GetMapping("/add")
    public String getAddForm(Model model){
        model.addAttribute("fda418", fda418Service.getAll());
        return "fda418-add";
    }

    @PostMapping("/add")
    public String addFDA418(@ModelAttribute FDA418 fda418, Model model, RedirectAttributes redirectAttrs) {
        // พอรับเข้ามาจะเอาเข้า List
        if(checkNum(fda418.getSum_benzoic_sorbic(), fda418.getBenzoic_c(), fda418.getSorbic_c(), fda418.getSynt_s(), fda418.getSynt_c()
                , fda418.getSod_s(), fda418.getSod_c(), fda418.getPotas_s(), fda418.getPotas_c(), fda418.getPlate_s(), fda418.getPlate_c())) {
            if (checkAddress(fda418.getProductName(), fda418.getR_name(), fda418.getSum_benzoic_sorbic(), fda418.getBenzoic_c()
                    , fda418.getSorbic_c(), fda418.getBenzoic_sorbic_f(), fda418.getSynt_s(), fda418.getSynt_c(), fda418.getSynt_f()
            , fda418.getSod_s(), fda418.getSod_c(), fda418.getSod_f(), fda418.getPotas_s(), fda418.getPotas_c(), fda418.getPotas_f()
            , fda418.getPlate_s(), fda418.getPlate_c(), fda418.getPlate_f())) {
                if(fda418Service.checkNameFDA(fda418.getProductName())) {
                    fda418Service.addFDA418(fda418);
                    return "redirect:/fda418/list";
                } else {
                    redirectAttrs.addFlashAttribute("error","Please don't use existing same products!");
                    return "redirect:/fda418/add";
                }
            } else {
                redirectAttrs.addFlashAttribute("error", "Please fill all the information fields!");
                return "redirect:/fda418/add";
            }
        } else {
            redirectAttrs.addFlashAttribute("error", "negative number is not allowed!");
            return "redirect:/fda418/add";
        }
    }

    public boolean checkNum(double sum_benzoic_sorbic, double benzoic_c, double sorbic_c, double synt_s, double synt_c, double sod_s, double sod_c, double potas_s, double potas_c, double plate_s, double plate_c){ //Method ดักห้ามใส่จำนวนเป็น 0
        if(sum_benzoic_sorbic >= 0 && benzoic_c >= 0 && sorbic_c >= 0 && synt_s >= 0 && synt_c >= 0 && sod_s >= 0 && sod_c >= 0  && potas_s >= 0 && potas_c >= 0 && plate_s >= 0 && plate_c >= 0){
            return true;
        }
        return false;
    }

    public boolean checkAddress(String productName, String r_name, double sum_benzoic_sorbic, double benzoic_c, double sorbic_c
            , String benzoic_sorbic_f, double synt_s, double synt_c, String synt_f, double sod_s, double sod_c, String sod_f
            , double potas_s, double potas_c, String potas_f, double plate_s, double plate_c, String plate_f){
        if (productName.equals("") || r_name.equals("") || String.valueOf(sum_benzoic_sorbic).equals("") || String.valueOf(benzoic_c).equals("") || String.valueOf(sorbic_c).equals("")
                || benzoic_sorbic_f.equals("") || String.valueOf(synt_s).equals("") || String.valueOf(synt_c).equals("") || synt_f.equals("")
                || String.valueOf(sod_s).equals("") || String.valueOf(sod_c).equals("") || String.valueOf(sod_f).equals("")
                || String.valueOf(potas_s).equals("") || String.valueOf(potas_c).equals("") || potas_f.equals("")
                || String.valueOf(plate_s).equals("") || String.valueOf(plate_c).equals("") || plate_f.equals("")){
            return false;
        }return true;
    }

    @GetMapping("/remove/{id}")
    public String removeFDA418(@PathVariable UUID id, Model model,Authentication authentication){
        FDA418 set = fda418Service.getOneById(id);
        fda418Service.delete(set);
        return "redirect:/fda418/list";
    }
}
