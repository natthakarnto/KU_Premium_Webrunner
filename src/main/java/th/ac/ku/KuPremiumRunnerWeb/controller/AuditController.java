package th.ac.ku.KuPremiumRunnerWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import th.ac.ku.KuPremiumRunnerWeb.model.Audit;
import th.ac.ku.KuPremiumRunnerWeb.service.AuditService;
import th.ac.ku.KuPremiumRunnerWeb.service.CakesService;
import th.ac.ku.KuPremiumRunnerWeb.service.UserService;

import java.util.UUID;

@Controller
@RequestMapping("/audit")
public class AuditController {

    @Autowired
    private AuditService auditService;
    @Autowired
    private CakesService cakesService;
    @Autowired
    private UserService userServices;

    @GetMapping("/edit/{id}")
    public String getEditForm(@PathVariable UUID id, Model model) {
        Audit audit = auditService.getOneById(id);
        model.addAttribute("audit", audit);
        return "audit-edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Audit audit, Model model, RedirectAttributes redirectAttrs) {
        if (checkAddress(audit.getProductName(), audit.getFda_356_Att(), audit.getFda_356_Res(), audit.getFda_356_Cer(), audit.getFda_414_Att(),
                audit.getFda_414_Res(), audit.getFda_414_Cer(), audit.getFda_416_Att(), audit.getFda_416_Res(), audit.getFda_416_Cer(),
                audit.getFda_418_Att(), audit.getFda_418_Res(), audit.getFda_418_Cer())) {
            if(checkTrueFalse(audit.getFda_356_Cer(), audit.getFda_414_Cer(), audit.getFda_416_Cer(), audit.getFda_418_Cer())) {
                auditService.update(audit);
                return "redirect:/audit/list";
            }else {
                redirectAttrs.addFlashAttribute("error", "Please type only Pass or Not Pass!");
                return "redirect:/audit/list";
            }
        } else {
            redirectAttrs.addFlashAttribute("error", "Please fill all the information fields!");
            return "redirect:/audit/list";
        }
    }

    @GetMapping("/list") //getAll
    public String getListForm(Model model, Authentication authentication) {
        model.addAttribute("rings12", auditService.getAll());
        return "audit-list";
    }

    @GetMapping
    public String getCakes(Model model, Authentication authentication) {
        userServices.setLoginUser(authentication.getName());
        model.addAttribute("audit", auditService.getAll());
        return "audit-edit";
    }

    @GetMapping("/add")
    public String getAddForm(Model model){
        model.addAttribute("audit", auditService.getAll());
        return "audit-add";
    }

    @PostMapping("/add")
    public String addAudit(@ModelAttribute Audit audit, Model model, RedirectAttributes redirectAttrs) {
        if (checkAddress(audit.getProductName(), audit.getFda_356_Att(), audit.getFda_356_Res(), audit.getFda_356_Cer(), audit.getFda_414_Att(),
                audit.getFda_414_Res(), audit.getFda_414_Cer(), audit.getFda_416_Att(), audit.getFda_416_Res(), audit.getFda_416_Cer(),
                audit.getFda_418_Att(), audit.getFda_418_Res(), audit.getFda_418_Cer())) {
            if(auditService.checkNameAudit(audit.getProductName())) {
                if(checkTrueFalse(audit.getFda_356_Cer(), audit.getFda_414_Cer(), audit.getFda_416_Cer(), audit.getFda_418_Cer())) {
                    auditService.addAudit(audit);
                    return "redirect:/audit/list";
                }else {
                    redirectAttrs.addFlashAttribute("error", "Please type only Pass or Not Pass!");
                    return "redirect:/audit/add";
                }
            } else {
                redirectAttrs.addFlashAttribute("error","Please don't use existing same products!");
                return "redirect:/audit/add";
            }
        } else {
            redirectAttrs.addFlashAttribute("error", "Please fill all the information fields!");
            return "redirect:/audit/add";
        }
    }

    public boolean checkTrueFalse(String fda_356_Cer, String fda_414_Cer, String fda_416_Cer, String fda_418_Cer) {
        if (fda_356_Cer.equals("True") || fda_356_Cer.equals("False") || fda_356_Cer.equals("true") || fda_356_Cer.equals("false") || fda_356_Cer.equals("T") || fda_356_Cer.equals("t") || fda_356_Cer.equals("F") || fda_356_Cer.equals("f") ||
                fda_414_Cer.equals("True") || fda_414_Cer.equals("False") || fda_414_Cer.equals("true") || fda_414_Cer.equals("false") || fda_414_Cer.equals("T") || fda_414_Cer.equals("t") || fda_414_Cer.equals("F") || fda_414_Cer.equals("f") ||
                fda_416_Cer.equals("True") || fda_416_Cer.equals("False") || fda_416_Cer.equals("true") || fda_416_Cer.equals("false") || fda_416_Cer.equals("T") || fda_416_Cer.equals("t") || fda_416_Cer.equals("F") || fda_416_Cer.equals("f") ||
                fda_418_Cer.equals("True") || fda_418_Cer.equals("False") || fda_418_Cer.equals("true") || fda_418_Cer.equals("false") || fda_418_Cer.equals("T") || fda_418_Cer.equals("t") || fda_418_Cer.equals("F") || fda_418_Cer.equals("f")) {
            return true;
        }
        return false;
    }

    public boolean checkAddress(String productName, String fda_356_Att, String fda_356_Res, String fda_356_Cer, String fda_414_Att,
                                String fda_414_Res, String fda_414_Cer, String fda_416_Att, String fda_416_Res, String fda_416_Cer,
                                String fda_418_Att, String fda_418_Res, String fda_418_Cer){
        if (productName.equals("") || fda_356_Att.equals("") || fda_356_Res.equals("") || fda_356_Cer.equals("") || fda_414_Att.equals("") ||
                fda_414_Res.equals("") || fda_414_Cer.equals("") || fda_416_Att.equals("") || fda_416_Res.equals("") || fda_416_Cer.equals("") ||
                fda_418_Att.equals("") || fda_418_Res.equals("") || fda_418_Cer.equals("")){
            return false;
        }return true;
    }

    @GetMapping("/remove/{id}")
    public String removeInspection(@PathVariable UUID id, Model model,Authentication authentication){
        Audit set = auditService.getOneById(id);
        auditService.delete(set);
        return "redirect:/audit/list";
    }
}
