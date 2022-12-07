package th.ac.ku.KuPremiumRunnerWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.KuPremiumRunnerWeb.model.Cart;
import th.ac.ku.KuPremiumRunnerWeb.model.Cakes;
import th.ac.ku.KuPremiumRunnerWeb.model.Certificate;
import th.ac.ku.KuPremiumRunnerWeb.model.Order;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class CakesService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;
    @Autowired
    private CertificateService certificateService;

    private List<Cakes> rings = new ArrayList<>();

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
        Cakes cakesOrder = new Cakes();
        cakesOrder = cakes;
        cakesOrder.setUsername(userService.getUser().getUsername());
        cakesOrder.setPoID(String.valueOf(userService.getUser().getUser_id()));
//        cakesOrder.setPcID(String.valueOf(certificateService.getCertificate().getProdCertificateID()));
//        System.out.println(certificateService.getCertificate().getProdCertificateID());
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
        Cakes cakesOrder = new Cakes();
        cakesOrder = cakes;
        cakesOrder.setUsername(userService.getUser().getUsername());
        cakesOrder.setPcID(String.valueOf(certificateService.getCertificate().getProdCertificateID()));
        restTemplate.put(url, cakes, Cakes.class);
    }

    public void OrderConfig(){
        rings = this.getAll();
        for(int i = 0; i < this.getAll().size(); i++){
            String hee = new String("");
            //hee = String.valueOf(this.getAll().get(i).getId());
            Cakes ring = new Cakes(this.getAll().get(i).getpID(),this.getAll().get(i).getProductName() ,this.getAll().get(i).getUsername(),this.getAll().get(i).getProductCategory()
            ,this.getAll().get(i).getPrice(), this.getAll().get(i).getPoID(), this.getAll().get(i).getProductDescription()
            ,this.getAll().get(i).getProductAttrib(), this.getAll().get(i).getProductUsageGuideline(), this.getAll().get(i).getProductIngredients()
            ,this.getAll().get(i).getProductNutrition(), this.getAll().get(i).getProductUseIndication(), this.getAll().get(i).getProductQuantity()
            ,this.getAll().get(i).getProductSize(), this.getAll().get(i).getProductVolume(), this.getAll().get(i).getProductWeight()
            ,this.getAll().get(i).getProductPromotion(), this.getAll().get(i).getProductDiscountPercent(), this.getAll().get(i).getPriceExcludingVat()
            ,this.getAll().get(i).getPricePromotion() ,this.getAll().get(i).getPcID(), this.getAll().get(i).getPrr_ID(), this.getAll().get(i).getPsvID()
            ,this.getAll().get(i).getFtvID(), this.getAll().get(i).getaID(),this.getAll().get(i).getRreID());
            rings.get(i).add(ring);
        }
    }

    public List<Cakes> getDummy(String name){ //getAll
        this.OrderConfig();
        List<Cakes> cart2 = new ArrayList<>();
        if (name.equals("admin")){
            return rings;
        }
        else {
            for (int i =0; i<rings.size();i++){
                if (name.equals(rings.get(i).getUsername())){
                    cart2.add(rings.get(i));
                }
            }
        }
        return cart2;
    }

    public boolean checkNameProduct(String name){
        this.OrderConfig();
        List<Cakes> cart2 = new ArrayList<>();
        for (int i =0; i<rings.size();i++) {
            if (name.equals(rings.get(i).getProductName())) {
                return false;
            }
        }
        return true;
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
