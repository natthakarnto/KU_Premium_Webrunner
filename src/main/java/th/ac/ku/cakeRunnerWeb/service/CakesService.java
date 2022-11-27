package th.ac.ku.cakeRunnerWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.cakeRunnerWeb.model.Cart;
import th.ac.ku.cakeRunnerWeb.model.Cakes;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class CakesService {

    @Autowired
    private RestTemplate restTemplate;

    public List<Cakes> getAll(){
        String url = "http://localhost:8090/cakes";
        ResponseEntity<Cakes[]> response = restTemplate.getForEntity(url, Cakes[].class);
        Cakes[] cakes = response.getBody();
        return Arrays.asList(cakes);
    }

    public List<Cart> getOrder(){
        String url = "http://localhost:8090/cakes";
        ResponseEntity<Cakes[]> response = restTemplate.getForEntity(url, Cakes[].class);
        Cakes[] cakes = response.getBody();
        ArrayList orders = new ArrayList();
        for(int i = 0 ; i < cakes.length; i++){
            orders.add(new Cart(cakes[i]));
        }
        return orders;
    }

    public void addCakes(Cakes cakes){
        String url = "http://localhost:8090/cakes";
        restTemplate.postForObject(url, cakes, Cakes.class );
    }

    public Cakes getOneById(UUID id){
        String url = "http://localhost:8090/cakes/" + id;
        ResponseEntity<Cakes> response =
                restTemplate.getForEntity(url, Cakes.class);
        Cakes cakes = response.getBody();
        return cakes;
    }
    public void update(Cakes cakes){
        String url = "http://localhost:8090/cakes/" + cakes.getpID();
        restTemplate.put(url, cakes, Cakes.class);
    }

//    public void updateCart(List<Cart> cart){ //Method อัพเดทเลขจำนวนเวลากด Add
//        for (int i = 0 ; i < cart.size(); i++){
//            Cakes update = this.getOneById(cart.get(i).getCakes().getId());
//            update.setAmount(update.getAmount()-cart.get(i).getQuantity());
//            String url = "http://localhost:8090/cakes/" + update.getId();
//            if(update.getAmount() != 0){
//                restTemplate.put(url, update, Cakes.class);
//            }
//            else {
//                restTemplate.delete(url,update,Cakes.class);
//            }
//        }
//    }
}
