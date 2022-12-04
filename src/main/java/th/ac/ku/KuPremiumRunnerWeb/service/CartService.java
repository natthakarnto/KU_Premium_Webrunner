package th.ac.ku.KuPremiumRunnerWeb.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.ac.ku.KuPremiumRunnerWeb.model.Cakes;
import th.ac.ku.KuPremiumRunnerWeb.model.Cart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static java.lang.Integer.parseInt;


@Service
public class CartService {
    @Autowired
    private CakesService cakesService;

    private List<Cart> cart = new ArrayList<>();
    // TODO: 2/12/2565  ถ้าเหลือเวลสมากพอ กลับมาทำ method เพื่อกันการ add product ซ้ำ
    public void addCart(UUID id){
        cart.add(new Cart(cakesService.getOneById(id)));
    }

//    public void OrderConfig(){
//        for(int i = 0; i < this.getAll().size(); i++){
//            String hee = new String("");
//            String hee2 = new String("");
//            hee = this.getAll().get(i).getCakes();
//            hee2 = this.getAll().get(i).getName();
//            hee = hee.replace("[", "").replace("]","");
//            hee = hee.replace("{","[").replace("}","]");
//            hee = hee.replace("[","").replace("]","");
//            String[] split = hee.split(",");
//            List<String> list = Arrays.asList(split);
//            for (int j = 0;j < list.size();j++){
//                //String hee2 =list.get(j);
//                split = list.get(j).trim().split("->");
//                List<String> list1 = Arrays.asList(split);
//                for (int k =0;k<list1.size();k++){
//                }
//                Cakes cakes = new Cakes(UUID.fromString(list1.get(0)),list1.get(1),list1.get(2),Double.parseDouble(list1.get(3))
//                        ,list1.get(4), list1.get(5), list1.get(6), list1.get(7), list1.get(8), list1.get(9), list1.get(10)
//                        ,parseInt(list1.get(11)), list1.get(12),list1.get(13),list1.get(14), list1.get(15), Double.parseDouble(list1.get(16))
//                        ,Double.parseDouble(list1.get(17)), Double.parseDouble(list1.get(18)), list1.get(19), list1.get(20),
//                        list1.get(21), list1.get(22), list1.get(23), list1.get(24));
//
//                cart.get(i).add(new Cart(cakes));
////                cart.get(i).add(new Cart(cakes,parseInt(list1.get(5))));
//            }
//        }
//    }

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
