package th.ac.ku.KuPremiumRunnerWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.KuPremiumRunnerWeb.model.FDA418;
import th.ac.ku.KuPremiumRunnerWeb.model.Inspection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class InspectionService {

    @Autowired
    private RestTemplate restTemplate;

    private List<Inspection> rings = new ArrayList<>();

    public List<Inspection> getAll(){
        String url = "http://localhost:8090/inspection";
        ResponseEntity<Inspection[]> response = restTemplate.getForEntity(url, Inspection[].class);
        Inspection[] inspections = response.getBody();
        return Arrays.asList(inspections);
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
    public void addInspection(Inspection inspection){
        String url = "http://localhost:8090/inspection";
        restTemplate.postForObject(url, inspection, Inspection.class );
    }

    public Inspection getOneById(UUID id){
        String url = "http://localhost:8090/inspection/" + id;
        ResponseEntity<Inspection> response =
                restTemplate.getForEntity(url, Inspection.class);
        Inspection inspection = response.getBody();
        return inspection;
    }
    public void update(Inspection inspection){
        String url = "http://localhost:8090/inspection/" + inspection.getTrack_No();
        restTemplate.put(url, inspection, Inspection.class);
    }

    public void OrderConfig(){
        rings = this.getAll();
        for(int i = 0; i < this.getAll().size(); i++){
            String hee = new String("");
            Inspection ring = new Inspection(this.getAll().get(i).getTrack_No(),this.getAll().get(i).getProductName()
                    , this.getAll().get(i).getSent_Date(), this.getAll().get(i).getR_Name(), this.getAll().get(i).getR_Rank()
                    , this.getAll().get(i).getNote(), this.getAll().get(i).getStatus());
            rings.get(i).add(ring);
        }
    }

    public boolean checkNameInspection(String name){
        this.OrderConfig();
        List<Inspection> cart2 = new ArrayList<>();
        for (int i =0; i<rings.size();i++) {
            if (name.equals(rings.get(i).getProductName())) {
                return false;
            }
        }
        return true;
    }

    public List<Inspection> getDummy(String name, String nameRing){ //getAll
        this.OrderConfig();
        List<Inspection> cart2 = new ArrayList<>();
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

    public void delete(Inspection rings) {
        String url = "http://localhost:8090/inspection/" + rings.getTrack_No();
        restTemplate.delete(url, rings, Inspection.class);
    }
}
