package th.ac.ku.KuPremiumRunnerWeb.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.ac.ku.KuPremiumRunnerWeb.model.Cart;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class CartService {
    @Autowired
    private CakesService cakesService;

    private List<Cart> cart = new ArrayList<>();
    // TODO: 2/12/2565  ถ้าเหลือเวลสมากพอ กลับมาทำ method เพื่อกันการ add product ซ้ำ
    public void addCart(UUID id){
        cart.add(new Cart(cakesService.getOneById(id)));
    }
    public List<Cart> getCart() {
        return cart;
    }

    public void removeCart(UUID id){
        for(int i = 0 ; i < cart.size(); i++){
            if (id.equals(cart.get(i).getCakes().getpID())){
                cart.remove(i);
            }

        }
    }

//    public void addCart(UUID id) {
//        List<Cart> cartList= new ArrayList<>();
//
////        cart.add(new Cart(cakesService.getOneById(id)));
////        for(int i = 0 ; i < cart.size(); i++){
////            if (id.equals(cart.get(i).getCakes().getpID())){
////                System.out.println("Duplicated ID");
////            }
////            else {
////                cart.add(new Cart(cakesService.getOneById(id)));
////            }
////        }
//    }

//    public int priceCalculate(){
//        int total = 0;
//        for (int i = 0 ; i< cart.size(); i++){
//            total += (cart.get(i).getQuantity() * cart.get(i).getCakes().getPrice());
//        }
//        return total;
//    }
    public void removeall(){
        cart = new ArrayList<>();
    }

}
