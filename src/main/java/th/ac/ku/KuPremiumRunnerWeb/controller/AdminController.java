package th.ac.ku.KuPremiumRunnerWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import th.ac.ku.KuPremiumRunnerWeb.model.Admin;
import th.ac.ku.KuPremiumRunnerWeb.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdminService service;

    @GetMapping
    public String getAdmins(Model model)
    {
        model.addAttribute("admins", service.getAll());
        return "admins";
    }

    @GetMapping("/add")
    public String getAddForm(){
        return "admin-register";
    }

    @PostMapping("/add")
    public String addAdmin(@ModelAttribute Admin admin, Model model) {
        // พอรับเข้ามาจะเอาเข้า List
        service.addAdmin(admin);

        return "redirect:/admin";
    }
}
