package th.ac.ku.KuPremiumRunnerWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import th.ac.ku.KuPremiumRunnerWeb.model.Certificate;
import th.ac.ku.KuPremiumRunnerWeb.service.CertificateService;
import th.ac.ku.KuPremiumRunnerWeb.service.UserService;

import java.util.UUID;

@Controller
@RequestMapping("/certificate")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private UserService userServices;

    @GetMapping("/edit/{id}")
    public String getEditForm(@PathVariable UUID id, Model model) {
        Certificate certificate = certificateService.getOneById(id);
        model.addAttribute("certificate", certificate);
        return "cakes-edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Certificate certificate, Model model) {
        return "redirect:/cakes/edit";
    }

    @GetMapping
    public String getCakes(Model model, Authentication authentication)
    {
        userServices.setLoginUserCakes(authentication.getName());
        model.addAttribute("certificate", certificateService.getAll());
        return "cakes-edit";
    }

    @GetMapping("/add")
    public String getAddForm(){
        return "certificate-add";
    }

    @PostMapping("/add")
    public String addCertificate(@ModelAttribute Certificate certificate, Model model, RedirectAttributes redirectAttrs) {
        // พอรับเข้ามาจะเอาเข้า List
        if(checkAddress(certificate.getProdCertificateName())) {
            certificateService.addCertificate(certificate);
            return "redirect:/cakes/edit";
        }
        else {
            redirectAttrs.addFlashAttribute("error","Please complete the information.");
            return "redirect:/certificate/add";
        }
    }

    public boolean checkAddress(String prodCertificateName){
        if (prodCertificateName.equals("")){
            return false;
        }return true;
    }
}
