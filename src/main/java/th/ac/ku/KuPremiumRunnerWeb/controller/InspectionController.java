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
//        if(checkNum(fda418.getSum_benzoic_sorbic(), fda418.getBenzoic_c(), fda418.getSorbic_c(), fda418.getSynt_s(), fda418.getSynt_c()
//                , fda418.getSod_s(), fda418.getSod_c(), fda418.getPotas_s(), fda418.getPotas_c(), fda418.getPlate_s(), fda418.getPlate_c())) {
        if (checkAddress(inspection.getProductName(), inspection.getSent_Date(), inspection.getR_Name(), inspection.getR_Rank()
                , inspection.getNote(), inspection.getStatus())) {
//                if(fda418Service.checkNameFDA(fda418.getProductName())) {
                inspectionService.addInspection(inspection);
                return "redirect:/inspection/list";
//                } else {
//                    redirectAttrs.addFlashAttribute("error","Please don't use existing same products!");
//                    return "redirect:/fda418/add";
//                }
            } else {
                redirectAttrs.addFlashAttribute("error", "Please fill all the information fields!");
                return "redirect:/inspection/list";
            }
//        } else {
//            redirectAttrs.addFlashAttribute("error", "negative number is not allowed!");
//            return "redirect:/inspection/edit";
//        }
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
        return "inspection-edit";
    }

    @GetMapping("/add")
    public String getAddForm(Model model){
        model.addAttribute("inspection", inspectionService.getAll());
        return "inspection-add";
    }

    @PostMapping("/add")
    public String addInspection(@ModelAttribute Inspection inspection, Model model, RedirectAttributes redirectAttrs) {
            if (checkAddress(inspection.getProductName(), inspection.getSent_Date(), inspection.getR_Name(), inspection.getR_Rank()
                    , inspection.getNote(), inspection.getStatus())) {
                if(inspectionService.checkNameInspection(inspection.getProductName())) {
                    inspectionService.addInspection(inspection);
                    return "redirect:/audit/list";
                } else {
                    redirectAttrs.addFlashAttribute("error","Please don't use existing same products!");
                    return "redirect:/audit/add";
                }
            } else {
                redirectAttrs.addFlashAttribute("error", "Please fill all the information fields!");
                return "redirect:/inspection/add";
            }
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
