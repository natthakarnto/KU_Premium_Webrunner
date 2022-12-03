package th.ac.ku.KuPremiumRunnerWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.KuPremiumRunnerWeb.model.Cakes;
import th.ac.ku.KuPremiumRunnerWeb.model.Certificate;
import th.ac.ku.KuPremiumRunnerWeb.model.FDA356;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class FDA356Service {

    @Autowired
    private RestTemplate restTemplate;

    private List<FDA356> rings = new ArrayList<>();

    public List<FDA356> getAll(){
        String url = "http://localhost:8090/fda356";
        ResponseEntity<FDA356[]> response = restTemplate.getForEntity(url, FDA356[].class);
        FDA356[] fda356s = response.getBody();
        return Arrays.asList(fda356s);
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
    public void addFDA356(FDA356 fda356){
        String url = "http://localhost:8090/fda356";
        restTemplate.postForObject(url, fda356, FDA356.class );
    }

    public FDA356 getOneById(UUID id){
        String url = "http://localhost:8090/fda356/" + id;
        ResponseEntity<FDA356> response =
                restTemplate.getForEntity(url, FDA356.class);
        FDA356 fda356 = response.getBody();
        return fda356;
    }
    public void update(FDA356 fda356){
        String url = "http://localhost:8090/fda356/" + fda356.getFda356ID();
        restTemplate.put(url, fda356, FDA356.class);
    }

    public void OrderConfig(){
        rings = this.getAll();
        for(int i = 0; i < this.getAll().size(); i++){
            String hee = new String("");
            FDA356 ring = new FDA356(this.getAll().get(i).getFda356ID(),this.getAll().get(i).getProductName()
                    , this.getAll().get(i).getR_name(), this.getAll().get(i).getPh_value_s(), this.getAll().get(i).getPh_value_c()
                    , this.getAll().get(i).getPh_value_f(), this.getAll().get(i).getColi_s(), this.getAll().get(i).getColi_c()
                    , this.getAll().get(i).getColi_f(), this.getAll().get(i).getYeast_mold_s(), this.getAll().get(i).getYeast_mold_r()
                    , this.getAll().get(i).getYeast_c(), this.getAll().get(i).getMold_c(), this.getAll().get(i).getYeast_mold_f());
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

    public List<FDA356> getDummy(String name, String nameRing){ //getAll
        this.OrderConfig();
        List<FDA356> cart2 = new ArrayList<>();
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
    public void delete(FDA356 rings) {
        String url = "http://localhost:8090/fda356/" + rings.getFda356ID();
        restTemplate.delete(url, rings, FDA356.class);
    }
}
