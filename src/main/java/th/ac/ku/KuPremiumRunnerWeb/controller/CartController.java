package th.ac.ku.KuPremiumRunnerWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.KuPremiumRunnerWeb.model.Cart;
import th.ac.ku.KuPremiumRunnerWeb.service.CartService;
import th.ac.ku.KuPremiumRunnerWeb.service.CakesService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private CakesService cakesService;

    @GetMapping("")
    public String index(Model model) {
//        int total = cartService.priceCalculate();
//        model.addAttribute("priceCal", total);
        model.addAttribute("price",cartService.getCart().size());
        model.addAttribute("carts",cartService.getCart());
        return "carts";
    }

    @PostMapping("")
    public String addCart(@ModelAttribute("num") int number, @ModelAttribute("productName") String productName, @ModelAttribute("name") UUID id, Model model) {
        System.out.println(productName);
        if (number == 0) {
//            if (!exists(id, cartService.getCart(), number)) {
//                cartService.addCart(id,number);
                cartService.addCart(id);
//            }
        }
        model.addAttribute("carts", cartService.getCart());
        return "redirect:/cakes";
    }
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable UUID id, Model model) {
        cartService.removeCart(id);
        model.addAttribute("carts",cartService.getCart());
        return "redirect:/cart";
    }
//    private boolean exists(UUID id, List<Cart> cart, int number) { //Method เช็คจำนวนที่จะนำเข้าไปใน Cart
//
//        for (int i = 0; i < cart.size(); i++) {
//            if (cart.get(i).getCakes().getpID().equals(id)) {
////                System.out.println(cart.get(i).getQuantity());
//                if (cart.get(i).getQuantity()+number<=(cakesService.getOneById(id).getAmount())){
//                    cart.get(i).setQuantity(cart.get(i).getQuantity()+number);
//                }
//                else if (cart.get(i).getQuantity()+number > (cakesService.getOneById(id).getAmount())){
//                    cart.get(i).setQuantity(cakesService.getOneById(id).getAmount());
//                }
//                return true;
//            }
//        }
//        return false;
//    }
}
