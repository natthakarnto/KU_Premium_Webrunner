//package th.ac.ku.KuPremiumRunnerWeb.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import th.ac.ku.KuPremiumRunnerWeb.model.Certificate;
//import th.ac.ku.KuPremiumRunnerWeb.model.Research;
//import th.ac.ku.KuPremiumRunnerWeb.service.CakesService;
//import th.ac.ku.KuPremiumRunnerWeb.service.CertificateService;
//import th.ac.ku.KuPremiumRunnerWeb.service.ResearchService;
//import th.ac.ku.KuPremiumRunnerWeb.service.UserService;
//
//import java.util.UUID;
//
//@Controller
//@RequestMapping("/research")
//public class ResearchController {
//
//    @Autowired
//    private ResearchService researchService;
//
//    @Autowired
//    private CakesService cakesService;
//
//    @Autowired
//    private UserService userServices;
//
//    @GetMapping("/edit/{id}")
//    public String getEditForm(@PathVariable UUID id, Model model) {
//        Research research = researchService.getOneById(id);
//        model.addAttribute("research", research);
//        return "research-edit";
//    }
//
//    @PostMapping("/edit")
//    public String edit(@ModelAttribute Certificate certificate, Model model) {
//        certificateService.update(certificate);
////        return "redirect:/certificate/list";
//        return "redirect:/certificate/list";
//    }
//}
