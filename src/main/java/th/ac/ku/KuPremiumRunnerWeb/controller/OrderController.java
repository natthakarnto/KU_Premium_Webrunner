package th.ac.ku.KuPremiumRunnerWeb.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.KuPremiumRunnerWeb.model.Order;
import th.ac.ku.KuPremiumRunnerWeb.service.OrderService;
import th.ac.ku.KuPremiumRunnerWeb.service.CakesService;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;
    private List<Order> cart = new ArrayList<>();

    @Autowired
    private CakesService cakesService;

    @GetMapping
    public String getCheckPage(Model model, Authentication authentication){
        model.addAttribute("cakeslist", service.getDummy(authentication.getName()));
        return "orders";
    }

    @GetMapping("/add")
    public String getAddForm(){
        return "order-add";
    }

    @PostMapping("/add")
    public String addOrder(@ModelAttribute Order order, Model model) {
        // พอรับเข้ามาจะเอาเข้า List
        service.addOrder(order);

        return "redirect:/order";
    }

    @GetMapping("/list/edit/{id}")
    public String editPayment(@PathVariable UUID id, Model model,Authentication authentication){
        Order set = service.getOneById(id);
//        set.setApprovedDate(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEE dd MMM yyyy HH:mm:ss"))));
//        Calendar calendar = Calendar.getInstance();
//        set.setApprovedDate(calendar.getTime());
//        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);
        set.setApprovedDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEE dd MMM yyyy HH:mm:ss")));
        set.setStatus("Approved");
//        cakesService.updateCart(service.getDummyByID(id).getCartList());
        service.update(set);
        return "redirect:/order";
    }

    @GetMapping("/list/unapproved/{id}")
    public String unapprovedPayment(@PathVariable UUID id, Model model,Authentication authentication){
        Order set = service.getOneById(id);
//        set.setApprovedDate(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEE dd MMM yyyy HH:mm:ss"))));
//        Calendar calendar = Calendar.getInstance();
//        set.setApprovedDate(calendar.getTime());
//        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);
        set.setApprovedDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEE dd MMM yyyy HH:mm:ss")));
        set.setStatus("Unapproved");
//        cakesService.updateCart(service.getDummyByID(id).getCartList());
        service.update(set);
        return "redirect:/order";
    }

    @GetMapping("/list/remove/{id}")
    public String removePayment(@PathVariable UUID id, Model model,Authentication authentication){
        Order set = service.getOneById(id);
        service.delete(set);
        return "redirect:/order";
    }

}
