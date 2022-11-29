package th.ac.ku.KuPremiumRunnerWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import th.ac.ku.KuPremiumRunnerWeb.model.Order;
import th.ac.ku.KuPremiumRunnerWeb.model.User;
import th.ac.ku.KuPremiumRunnerWeb.service.CartService;
import th.ac.ku.KuPremiumRunnerWeb.service.OrderService;
import th.ac.ku.KuPremiumRunnerWeb.service.CakesService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

@Controller
@RequestMapping("/order")
public class CheckListController {
    @Autowired
    private OrderService service;
    private int total;
    private Order order;
    @Autowired
    private CartService cartService;
    @Autowired
    private CakesService cakesService;

    @GetMapping("/list")
    public String getOrders(Model model)
    {
//        total = cartService.priceCalculate();
//        model.addAttribute("priceCal", total);
        model.addAttribute("price",cartService.getCart().size());
        model.addAttribute("carts",cartService.getCart());
        return "checklist";
    }

    @GetMapping("/address")
    public String getAddress(Model model){
//        int total = cartService.priceCalculate();
//        model.addAttribute("priceCal", total);
        model.addAttribute("price",cartService.getCart().size());
        return "address";
    }

    @PostMapping("/address")
    public String addOrder(Model model, @ModelAttribute Order order, @ModelAttribute User user, RedirectAttributes redirectAttrs){
        if (checkAddress(order)){
            redirectAttrs.addFlashAttribute("error","Please check your information fields!");
            return "redirect:/order/address";
        }
        else {
//            total = cartService.priceCalculate();
//            model.addAttribute("priceCal", total);
            order.setDate(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEE dd MMM yyyy HH:mm:ss"))));
            service.addOrder(order);
            cartService.removeall();
            return "redirect:/order";
        }

    }
    public boolean checkAddress(Order order){
        if ((order.getName().equals("")) || (order.getSurname().equals("")) || (order.getMobile().equals(""))) {
            return true;
        }return false;
    }
}
