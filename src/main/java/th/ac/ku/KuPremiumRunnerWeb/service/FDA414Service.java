package th.ac.ku.KuPremiumRunnerWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.KuPremiumRunnerWeb.model.FDA356;
import th.ac.ku.KuPremiumRunnerWeb.model.FDA414;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class FDA414Service {
    @Autowired
    private RestTemplate restTemplate;

    private List<FDA414> rings = new ArrayList<>();

    public List<FDA414> getAll(){
        String url = "http://localhost:8090/fda414";
        ResponseEntity<FDA414[]> response = restTemplate.getForEntity(url, FDA414[].class);
        FDA414[] fda414s = response.getBody();
        return Arrays.asList(fda414s);
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
    public void addFDA414(FDA414 fda414){
        String url = "http://localhost:8090/fda414";
        restTemplate.postForObject(url, fda414, FDA414.class );
    }

    public FDA414 getOneById(UUID id){
        String url = "http://localhost:8090/fda414/" + id;
        ResponseEntity<FDA414> response =
                restTemplate.getForEntity(url, FDA414.class);
        FDA414 fda414 = response.getBody();
        return fda414;
    }
    public void update(FDA414 fda414){
        String url = "http://localhost:8090/fda414/" + fda414.getFda414ID();
        restTemplate.put(url, fda414, FDA414.class);
    }

    public void OrderConfig(){
        rings = this.getAll();
        for(int i = 0; i < this.getAll().size(); i++){
            String hee = new String("");
            FDA414 ring = new FDA414(this.getAll().get(i).getFda414ID(),this.getAll().get(i).getProductName()
                    , this.getAll().get(i).getR_name(), this.getAll().get(i).getLead_s(), this.getAll().get(i).getLead_c()
                    , this.getAll().get(i).getLead_f());
            rings.get(i).add(ring);
        }
    }

    public boolean checkNameFDA(String name){
        this.OrderConfig();
        List<FDA356> cart2 = new ArrayList<>();
        for (int i =0; i<rings.size();i++) {
            if (name.equals(rings.get(i).getProductName())) {
                return false;
            }
        }
        return true;
    }

    public List<FDA414> getDummy(String name, String nameRing){ //getAll
        this.OrderConfig();
        List<FDA414> cart2 = new ArrayList<>();
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
    public void delete(FDA414 rings) {
        String url = "http://localhost:8090/fda414/" + rings.getFda414ID();
        restTemplate.delete(url, rings, FDA414.class);
    }
}
