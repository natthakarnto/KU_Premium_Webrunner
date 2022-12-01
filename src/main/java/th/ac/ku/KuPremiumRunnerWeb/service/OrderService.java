package th.ac.ku.KuPremiumRunnerWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.KuPremiumRunnerWeb.model.Order;
import th.ac.ku.KuPremiumRunnerWeb.model.Cart;
import th.ac.ku.KuPremiumRunnerWeb.model.Cakes;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static java.lang.Integer.parseInt;

@Service
public class OrderService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    private List<Order> cart = new ArrayList<>();

    private List<Cakes> cakes1 = new ArrayList<>();

    public List<Order> getAll(){
        String url = "http://localhost:8090/order";
        ResponseEntity<Order[]> response = restTemplate.getForEntity(url, Order[].class);
        Order[] cakes = response.getBody();
        return Arrays.asList(cakes);
    }

    public void addOrder(Order cake){
        String url = "http://localhost:8090/order";
        Order cakesOrder = new Order();
        cakesOrder = cake;
        cakesOrder.setUsername(userService.getUser().getUsername());
        cakesOrder.setCakes(cartService.getCart().toString());
        cakesOrder.setStatus("Unapproved");
        restTemplate.postForObject(url, cakesOrder, Order.class);
    }

    public void OrderConfig(){
        cart = this.getAll();
        for(int i = 0; i < this.getAll().size(); i++){
            String hee = new String("");
            String hee2 = new String("");
            hee = this.getAll().get(i).getCakes();
            hee2 = this.getAll().get(i).getName();
            hee = hee.replace("[", "").replace("]","");
            hee = hee.replace("{","[").replace("}","]");
            hee = hee.replace("[","").replace("]","");
            String[] split = hee.split(",");
            List<String> list = Arrays.asList(split);
            for (int j = 0;j < list.size();j++){
                //String hee2 =list.get(j);
                split = list.get(j).trim().split("->");
                List<String> list1 = Arrays.asList(split);
                for (int k =0;k<list1.size();k++){
                }
                Cakes cakes = new Cakes(UUID.fromString(list1.get(0)),list1.get(1),list1.get(2),Double.parseDouble(list1.get(3))
                        ,list1.get(4), list1.get(5), list1.get(6), list1.get(7), list1.get(8), list1.get(9), list1.get(10)
                        ,parseInt(list1.get(11)), list1.get(12),list1.get(13),list1.get(14), list1.get(15), Double.parseDouble(list1.get(16))
                        ,Double.parseDouble(list1.get(17)), Double.parseDouble(list1.get(18)), list1.get(19), list1.get(20),
                        list1.get(21), list1.get(22), list1.get(23), list1.get(24));

                cart.get(i).add(new Cart(cakes));
//                cart.get(i).add(new Cart(cakes,parseInt(list1.get(5))));
            }
        }
    }
    public List<Order> getDummy(String name){ //getAll
        this.OrderConfig();
        List<Order> cart2 = new ArrayList<>();
        if (name.equals("admin")){
            return cart;
        }
        else {
            for (int i =0; i<cart.size();i++){
                if (name.equals(cart.get(i).getUsername())){
                    cart2.add(cart.get(i));
                }
            }
        }
        return cart2;
    }
    public Order getDummyByID(UUID id){
        Order cakesOrder = new Order();
        for (int i=0;i < cart.size();i++){
            if (cart.get(i).getOrderId().equals(id)){
                cakesOrder = cart.get(i);
                return cakesOrder;
            }
        }
        return cakesOrder;
    }

    public Order getOneById(UUID id) {
        String url = "http://localhost:8090/order/" + id;
        ResponseEntity<Order> response =
                restTemplate.getForEntity(url, Order.class);
        Order cakes = response.getBody();
        return cakes;
    }
    public void update(Order cakes) {
        String url = "http://localhost:8090/order/" + cakes.getOrderId();
        restTemplate.put(url, cakes, Order.class);
    }
    public void delete(Order cakes) {
        String url = "http://localhost:8090/order/" + cakes.getOrderId();
        restTemplate.delete(url, cakes, Order.class);
    }
}
