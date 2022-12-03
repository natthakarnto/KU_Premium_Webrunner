package th.ac.ku.KuPremiumRunnerWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.KuPremiumRunnerWeb.model.FDA416;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class FDA416Service {
    @Autowired
    private RestTemplate restTemplate;

    private List<FDA416> rings = new ArrayList<>();

    public List<FDA416> getAll(){
        String url = "http://localhost:8090/fda416";
        ResponseEntity<FDA416[]> response = restTemplate.getForEntity(url, FDA416[].class);
        FDA416[] fda416s = response.getBody();
        return Arrays.asList(fda416s);
    }

    //    public List<Certificate> getOrder(){
//        String url = "http://localhost:8090/cakes";
//        ResponseEntity<Certificate[]> response = restTemplate.getForEntity(url, Certificate[].class);
//        Certificate[] certificates = response.getBody();
//        ArrayList orders = new ArrayList();
//        for(int i = 0 ; i < certificates.length; i++){
//            orders.add(new Cart(certificates[i]));
//        }
//        return orders;
//    }
//
    public void addFDA416(FDA416 fda416){
        String url = "http://localhost:8090/fda416";
        restTemplate.postForObject(url, fda416, FDA416.class );
    }

    public FDA416 getOneById(UUID id){
        String url = "http://localhost:8090/fda416/" + id;
        ResponseEntity<FDA416> response =
                restTemplate.getForEntity(url, FDA416.class);
        FDA416 fda416 = response.getBody();
        return fda416;
    }
    public void update(FDA416 fda416){
        String url = "http://localhost:8090/fda416/" + fda416.getFda416ID();
        restTemplate.put(url, fda416, FDA416.class);
    }

    public void OrderConfig(){
        rings = this.getAll();
        for(int i = 0; i < this.getAll().size(); i++){
            String hee = new String("");
            FDA416 ring = new FDA416(this.getAll().get(i).getFda416ID(),this.getAll().get(i).getProductName()
                    , this.getAll().get(i).getR_name(), this.getAll().get(i).getSalla_s(), this.getAll().get(i).getSalla_c()
                    , this.getAll().get(i).getSalla_f(), this.getAll().get(i).getSareus_s(), this.getAll().get(i).getSareus_c()
            , this.getAll().get(i).getSareus_f());
            rings.get(i).add(ring);
        }
    }

    public boolean checkNameFDA(String name){
        this.OrderConfig();
        List<FDA416> cart2 = new ArrayList<>();
        for (int i =0; i<rings.size();i++) {
            if (name.equals(rings.get(i).getProductName())) {
                return false;
            }
        }
        return true;
    }

    public List<FDA416> getDummy(String name, String nameRing){ //getAll
        this.OrderConfig();
        List<FDA416> cart2 = new ArrayList<>();
        if (name.equals("")){
            return rings;
        }
        else {
            for (int i =0; i<rings.size();i++) {
                if(name.equals("admin")) {
                    if (nameRing.equals(rings.get(i).getProductName())) {
                        cart2.add(rings.get(i));
                    }
                }

            }
        }
        return cart2;
    }
    public void delete(FDA416 rings) {
        String url = "http://localhost:8090/fda416/" + rings.getFda416ID();
        restTemplate.delete(url, rings, FDA416.class);
    }
}
