package th.ac.ku.KuPremiumRunnerWeb.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import th.ac.ku.KuPremiumRunnerWeb.model.Cakes;
import th.ac.ku.KuPremiumRunnerWeb.service.CakesService;
import th.ac.ku.KuPremiumRunnerWeb.service.FileUploadUtil;
import th.ac.ku.KuPremiumRunnerWeb.service.UserService;

import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/cakes")
public class CakesController {

    @Autowired
    private CakesService cakesService;
    @Autowired
    private UserService userServices;

    @GetMapping("/edit/{id}")
    public String getEditForm(@PathVariable UUID id, Model model) {
        Cakes cakes = cakesService.getOneById(id);
        model.addAttribute("cakes", cakes);
        return "cakes-edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Cakes cakes, Model model, RedirectAttributes redirectAttrs) {
        if(checkCake(cakes.getPrice(), cakes.getProductQuantity(), cakes.getProductDiscountPercent(), cakes.getPriceExcludingVat(), cakes.getPricePromotion())) {
            if(checkAddress(cakes.getProductName(), cakes.getProductCategory(),cakes.getProductDescription(),
                    cakes.getProductAttrib(), cakes.getProductUsageGuideline(),cakes.getProductIngredients(), cakes.getProductNutrition(),
                    cakes.getProductUseIndication(),cakes.getProductSize(), cakes.getProductVolume(), cakes.getProductWeight(),
                    cakes.getProductPromotion(), cakes.getPrr_ID(), cakes.getPsvID(),cakes.getFtvID(), cakes.getaID(),
                    cakes.getRreID())) {
                cakesService.update(cakes);
                return "redirect:/cakes";
            }
            redirectAttrs.addFlashAttribute("error","Please fill all the information fields!");
            return "redirect:/cakes";
        }
        redirectAttrs.addFlashAttribute("error","negative number is not allowed!");
        return "redirect:/cakes";
    }

    @GetMapping
    public String getCakes(Model model, Authentication authentication) {
        userServices.setLoginUser(authentication.getName());
        model.addAttribute("cakes", cakesService.getDummy(authentication.getName())); // เปลี่ยน getDummy
        return "cakes";
    }

//    @GetMapping
//    public String getCakesCertificate(Model model, Authentication authentication) {
//        userServices.setLoginUser(authentication.getName());
//        model.addAttribute("cakes", cakesService.getDummy(authentication.getName())); // เปลี่ยน getDummy
//        return "certificate-add";
//    }

    @GetMapping("/add")
    public String getAddForm(){
        return "cakes-add";
    }

    @PostMapping("/add")
    public String addCakes(@ModelAttribute Cakes cakes, Model model, RedirectAttributes redirectAttrs) {
        if(checkCake(cakes.getPrice(), cakes.getProductQuantity(), cakes.getProductDiscountPercent(), cakes.getPriceExcludingVat(), cakes.getPricePromotion())){
            if(checkAddress(cakes.getProductName(), cakes.getProductCategory(),cakes.getProductDescription(),
                    cakes.getProductAttrib(), cakes.getProductUsageGuideline(),cakes.getProductIngredients(), cakes.getProductNutrition(),
                    cakes.getProductUseIndication(),cakes.getProductSize(), cakes.getProductVolume(), cakes.getProductWeight(),
                    cakes.getProductPromotion(), cakes.getPrr_ID(), cakes.getPsvID(),cakes.getFtvID(), cakes.getaID(),
                    cakes.getRreID())) {
                if(cakesService.checkNameProduct(cakes.getProductName())) {
                    cakesService.addCakes(cakes);
                    return "redirect:/cakes"; //หญิง
                } else {
                    redirectAttrs.addFlashAttribute("error","Please don't use the same product name!");
                    return "redirect:/cakes/add";
                }
            } else {
                redirectAttrs.addFlashAttribute("error","Please fill all the information fields!");
                return "redirect:/cakes/add";
            }
        }
        else {
            redirectAttrs.addFlashAttribute("error","negative number is not allowed!");
            return "redirect:/cakes/add";
        }
    }

    public boolean checkCake(double price, int productQuantity, double productDiscountPercent, double priceExcludingVat, double pricePromotion){ //Method ดักห้ามใส่จำนวนเป็น 0
        if(price > 0 && productQuantity > 0 && productDiscountPercent > 0 && priceExcludingVat > 0 && pricePromotion > 0){
            return true;
        }
        return false;
    }

    public boolean checkAddress(String productName, String productCategory, String productDescription //Method ดักให้ใส่ข้อมูลให้ครบ
            ,String productAttrib, String productUsageGuideline, String productIngredients, String productNutrition, String productUseIndication
            ,String productSize, String productVolume, String productWeight, String productPromotion, String prr_ID,
                                String psvID, String ftvID, String aID, String rreID) {
        if (productName.equals("") || productCategory.equals("")  || productDescription.equals("")
                || productAttrib.equals("") || productUsageGuideline.equals("") || productIngredients.equals("") || productNutrition.equals("")
                || productUseIndication.equals("") || productSize.equals("") || productVolume.equals("")
                || productWeight.equals("") || productPromotion.equals("")
                || prr_ID.equals("") || psvID.equals("") || ftvID.equals("") || aID.equals("") || rreID.equals("")) {
            return false;
        }
        return true;
    }

//    @Autowired
//    private CakesRepository repo;
//
//    @PostMapping("/save")
//    public RedirectView saveUser(Cakes cakes,
//                                 @RequestParam("image") MultipartFile multipartFile) throws IOException {
//
//        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//        cakes.setPhotos(fileName);
//
//        Cakes savedCakes = repo.save(cakes);
//
//        String uploadDir = "../KU_Premium_Webrunner/src/main/resources/static/images/" + savedCakes.getpID();
//
//        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
//
//        return new RedirectView("/cakes/add", true);
//    }

}
