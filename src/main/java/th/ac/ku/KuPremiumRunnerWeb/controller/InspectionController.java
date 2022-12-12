package th.ac.ku.KuPremiumRunnerWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import th.ac.ku.KuPremiumRunnerWeb.model.Inspection;
import th.ac.ku.KuPremiumRunnerWeb.service.CakesService;
import th.ac.ku.KuPremiumRunnerWeb.service.InspectionService;
import th.ac.ku.KuPremiumRunnerWeb.service.UserService;

import java.util.UUID;

@Controller
@RequestMapping("/inspection")
public class InspectionController {
    @Autowired
    private InspectionService inspectionService;
    @Autowired
    private CakesService cakesService;
    @Autowired
    private UserService userServices;

    @GetMapping("/edit/{id}")
    public String getEditForm(@PathVariable UUID id, Model model) {
        Inspection inspection = inspectionService.getOneById(id);
        model.addAttribute("inspection", inspection);
        return "inspection-edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Inspection inspection, Model model, RedirectAttributes redirectAttrs) {
        if (checkAddress(inspection.getProductName(), inspection.getSent_Date(), inspection.getR_Name(),
                inspection.getR_Rank(), inspection.getNote(), inspection.getStatus())) {
                inspectionService.update(inspection);
                return "redirect:/inspection/list";
        } else {
            redirectAttrs.addFlashAttribute("error", "Please fill in the rest of the information fields!");
            return "redirect:/inspection/list";
        }
    }

    @GetMapping("/list") //getAll
    public String getListForm(Model model, Authentication authentication) {
        model.addAttribute("rings11", inspectionService.getAll());
        return "inspection-list";
    }

    @GetMapping
    public String getCakes(Model model, Authentication authentication) {
        userServices.setLoginUser(authentication.getName());
        model.addAttribute("inspection", inspectionService.getAll());
        model.addAttribute("productsInspection", cakesService.getDummy(authentication.getName()));
        return "inspection-edit";
    }

    @GetMapping("/add")
    public String getAddForm(Model model, Authentication authentication){
        model.addAttribute("inspection", inspectionService.getAll());
        model.addAttribute("productsInspection", cakesService.getDummy(authentication.getName()));
        return "inspection-add";
    }

    @PostMapping("/add")
    public String addInspection(@ModelAttribute Inspection inspection, Model model, RedirectAttributes redirectAttrs) {
            if (checkAddress(inspection.getProductName(), inspection.getSent_Date(), inspection.getR_Name(), inspection.getR_Rank()
                    , inspection.getNote(), inspection.getStatus())) {
                if(inspectionService.checkNameInspection(inspection.getProductName())) {
//                    if(checkTrueFalse(inspection.getStatus())) {
                        inspectionService.addInspection(inspection);
                        return "redirect:/inspection/list";
//                    }else {
//                        redirectAttrs.addFlashAttribute("error", "Please type in either \"Done\" or \"Not Done yet\"!");
//                        return "redirect:/inspection/add";
//                    }
                } else {
                    redirectAttrs.addFlashAttribute("error","Existed products!,please try again");
                    return "redirect:/inspection/add";
                }
            } else {
                redirectAttrs.addFlashAttribute("error", "Please fill in the rest of the information fields!");
                return "redirect:/inspection/add";
            }
    }

    public boolean checkTrueFalse(String status) {
        if (status.equals("Done") || status.equals("Not Done") || status.equals("done") || status.equals("not done") ||
                status.equals("notdone") || status.equals("Not done") || status.equals("not Done")) {
            return true;
        }
        return false;
    }

    public boolean checkAddress(String productName, String sent_Date, String r_Name, String r_Rank, String note, String status){
        if (productName.equals("") || sent_Date.equals("") || r_Name.equals("") || r_Rank.equals("") || note.equals("") || status.equals("")){
            return false;
        }return true;
    }

    @GetMapping("/remove/{id}")
    public String removeInspection(@PathVariable UUID id, Model model,Authentication authentication){
        Inspection set = inspectionService.getOneById(id);
        inspectionService.delete(set);
        return "redirect:/inspection/list";
    }
}
