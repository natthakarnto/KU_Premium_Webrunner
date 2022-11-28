package th.ac.ku.KuPremiumRunnerWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import th.ac.ku.KuPremiumRunnerWeb.model.User;
import th.ac.ku.KuPremiumRunnerWeb.service.UserService;

@Controller
@RequestMapping("")
public class LoginController {

    @Autowired
    private UserService userService;
//    private AdminService adminService;
    private User username;

    @RequestMapping("/register")
    public String getRegister(){
        return "register";
    }

//    @RequestMapping("/register_rdi")
//    public String getRegisterRDI(){
//        return "register-rdi";
//    }


    @PostMapping("/register")
    public String addUser(@ModelAttribute User user, Model model, RedirectAttributes redirectAttrs, @ModelAttribute("repassword") String repassword){

        if (!registerCheck(user,repassword)){
            redirectAttrs.addFlashAttribute("error","Please correct all information");
        }
        else {
            if (!exits(user)){
                redirectAttrs.addFlashAttribute("error","This email or Username has already exist");
            }
            else {
                if (!checkPassword(user.getPassword(),repassword)){
                    redirectAttrs.addFlashAttribute("error","Your password is not same");
                }
                else {
                    userService.addUser(user);
                    return "redirect:/login";
                }
            }
        }
        return "redirect:/register";
    }

//    @PostMapping("/register_rdi")
//    public String addAdmin(@ModelAttribute Admin admin, Model model, RedirectAttributes redirectAttrs, @ModelAttribute("repassword") String repassword){
//
//        if (!registerAdminCheck(admin,repassword)){
//            redirectAttrs.addFlashAttribute("error","Please correct all information");
//        }
//        else {
//            if (!exitsAdmin(admin)){
//                redirectAttrs.addFlashAttribute("error","This email or Username has already exist");
//            }
//            else {
//                if (!checkAdminPassword(admin.getPassword(),repassword)){
//                    redirectAttrs.addFlashAttribute("error","Your password is not same");
//                }
//                else {
//                    adminService.addAdmin(admin);
//                    return "redirect:/login";
//                }
//            }
//        }
//        return "redirect:/register_rdi";
//    }
    public boolean registerCheck(User user,String repassword){

        if ((user.getFirstName().equals("") || (user.getLastName().equals("") || user.getEmail().equals("")) || user.getTelNo().equals(""))
                || (user.getPassword().equals("")) || (user.getUsername().equals(""))
                || (repassword.equals("") || user.getCompanyName().equals("") || user.getCompanyAddress().equals(""))){
            return false;
        }
        return true;
    }

//    public boolean registerAdminCheck(Admin admin,String repassword){
//
//        if ((admin.getFirstName().equals("") || (admin.getLastName().equals("") || admin.getEmail().equals("")) || admin.getTelNo().equals(""))
//                || (admin.getPassword().equals("")) || (admin.getUsername().equals(""))
//                || (repassword.equals("") || admin.getResearcherRank().equals(""))){
//            return false;
//        }
//        return true;
//    }
    public boolean checkPassword(String password, String repassword){
        if (!password.equals(repassword)){
            return false;
        }
        return true;
    }

//    public boolean checkAdminPassword(String password, String repassword){
//        if (!password.equals(repassword)){
//            return false;
//        }
//        return true;
//    }
    public boolean exits(User user){
        System.out.println(userService.getAll()+"1111");
        for (int i = 0; i < userService.getAll().size();i++){
            System.out.println(userService.getAll()+"3333");
            if (user.getEmail().equals(userService.getAll().get(i).getEmail()) || user.getUsername().equals(userService.getAll().get(i).getUsername())){
                return false;
            }
        }
        return true;
    }

//    public boolean exitsAdmin(Admin admin){
//        System.out.println(adminService.getAll()+"2222");
//        for (int i = adminService.getAll().size()-1; i >= 0;i--){
//            System.out.println(userService.getAll()+"4444");
//            if (admin.getEmail().equals(adminService.getAll().get(i).getEmail()) || admin.getUsername().equals(adminService.getAll().get(i).getUsername())){
//                return false;
//            }
//        }
//        return true;
//    }

}
