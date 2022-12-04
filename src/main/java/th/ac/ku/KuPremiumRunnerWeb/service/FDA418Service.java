package th.ac.ku.KuPremiumRunnerWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.KuPremiumRunnerWeb.model.FDA416;
import th.ac.ku.KuPremiumRunnerWeb.model.FDA418;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class FDA418Service {
    @Autowired
    private RestTemplate restTemplate;

    private List<FDA418> rings = new ArrayList<>();

    public List<FDA418> getAll(){
        String url = "http://localhost:8090/fda418";
        ResponseEntity<FDA418[]> response = restTemplate.getForEntity(url, FDA418[].class);
        FDA418[] fda418s = response.getBody();
        return Arrays.asList(fda418s);
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
    public void addFDA418(FDA418 fda418){
        String url = "http://localhost:8090/fda418";
        restTemplate.postForObject(url, fda418, FDA418.class );
    }

    public FDA418 getOneById(UUID id){
        String url = "http://localhost:8090/fda418/" + id;
        ResponseEntity<FDA418> response =
                restTemplate.getForEntity(url, FDA418.class);
        FDA418 fda418 = response.getBody();
        return fda418;
    }
    public void update(FDA418 fda418){
        String url = "http://localhost:8090/fda418/" + fda418.getFda418ID();
        restTemplate.put(url, fda418, FDA418.class);
    }

    public void OrderConfig(){
        rings = this.getAll();
        for(int i = 0; i < this.getAll().size(); i++){
            String hee = new String("");
            FDA418 ring = new FDA418(this.getAll().get(i).getFda418ID(),this.getAll().get(i).getProductName()
                    , this.getAll().get(i).getR_name(), this.getAll().get(i).getSum_benzoic_sorbic(), this.getAll().get(i).getBenzoic_c()
                    , this.getAll().get(i).getSorbic_c(), this.getAll().get(i).getBenzoic_sorbic_f(), this.getAll().get(i).getSynt_s()
                    , this.getAll().get(i).getSynt_c(), this.getAll().get(i).getSynt_f(), this.getAll().get(i).getSod_s(), this.getAll().get(i).getSod_c()
                    , this.getAll().get(i).getSod_f(), this.getAll().get(i).getPotas_s(), this.getAll().get(i).getPotas_c()
                    , this.getAll().get(i).getPotas_f(), this.getAll().get(i).getPlate_s(), this.getAll().get(i).getPlate_c(), this.getAll().get(i).getPlate_f());
            rings.get(i).add(ring);
        }
    }

    public boolean checkNameFDA(String name){
        this.OrderConfig();
        List<FDA418> cart2 = new ArrayList<>();
        for (int i =0; i<rings.size();i++) {
            if (name.equals(rings.get(i).getProductName())) {
                return false;
            }
        }
        return true;
    }

    public List<FDA418> getDummy(String name, String nameRing){ //getAll
        this.OrderConfig();
        List<FDA418> cart2 = new ArrayList<>();
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

    public void delete(FDA418 rings) {
        String url = "http://localhost:8090/fda418/" + rings.getFda418ID();
        restTemplate.delete(url, rings, FDA418.class);
    }
}
