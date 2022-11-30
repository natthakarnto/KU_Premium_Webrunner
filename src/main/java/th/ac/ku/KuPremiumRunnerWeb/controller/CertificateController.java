package th.ac.ku.KuPremiumRunnerWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import th.ac.ku.KuPremiumRunnerWeb.model.Cakes;
import th.ac.ku.KuPremiumRunnerWeb.model.Certificate;
import th.ac.ku.KuPremiumRunnerWeb.service.CakesService;
import th.ac.ku.KuPremiumRunnerWeb.service.CertificateService;
import th.ac.ku.KuPremiumRunnerWeb.service.UserService;

import java.util.UUID;

@Controller
@RequestMapping("/certificate")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private CakesService cakesService;

    @Autowired
    private UserService userServices;

    @GetMapping("/edit/{id}")
    public String getEditForm(@PathVariable UUID id, Model model) {
        Certificate certificate = certificateService.getOneById(id);
        model.addAttribute("certificate", certificate);
        return "certificate-edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Certificate certificate, Model model) {
        certificateService.update(certificate);
//        return "redirect:/certificate/list";
        return "redirect:/certificate/list";
    }

//    @GetMapping("/remove/{id}")
//    public String removeRings(@PathVariable UUID id, Model model,Authentication authentication){
//        Certificate set = certificateService.getOneById(id);
//        certificateService.delete(set);
//        return "/cakes";
//    }

    @GetMapping("/list") //getAll
    public String getListForm(Model model, Authentication authentication) {
        //model.addAttribute("rings", rings);
        //model.addAttribute("rings2", ringsService.getDummy(authentication.getName(),rings.getName()));
        model.addAttribute("rings2", certificateService.getAll());
        return "certificate-list";
    }

    @GetMapping("/list/{id}") //getDummy
    public String getListIDForm(@PathVariable UUID id, Model model, Authentication authentication) {
        Certificate rings = certificateService.getOneById(id);
        //model.addAttribute("rings", rings);
        //model.addAttribute("rings2", ringsService.getDummy(authentication.getName(),rings.getName()));
        model.addAttribute("rings2", certificateService.getDummy(authentication.getName() ,rings.getProductName()));
        return "certificate-list";
    }

    @GetMapping
    public String getCakes(Model model, Authentication authentication)
    {
        userServices.setLoginUser(authentication.getName());
        model.addAttribute("certificate", certificateService.getAll());
        return "certificate-edit";
    }

    @GetMapping("/add")
    public String getAddForm(Model model){
        model.addAttribute("certificate", certificateService.getAll());
        return "certificate-add";
    }

    @PostMapping("/add")
    public String addCertificate(@ModelAttribute Certificate certificate, Model model, RedirectAttributes redirectAttrs) {
        // พอรับเข้ามาจะเอาเข้า List

        if(checkAddress(certificate.getProdCertificateName(), certificate.getProductName())) {
            certificateService.addCertificate(certificate);
            return "redirect:/certificate/list";
        }
        else {
            redirectAttrs.addFlashAttribute("error","Please fill all the information fields!");
            return "redirect:/certificate/add";
        }
    }

    public boolean checkAddress(String prodCertificateName, String productName){
        if (prodCertificateName.equals("") || (productName.equals(""))){
            return false;
        }return true;
    }
}
