package th.ac.ku.KuPremiumRunnerWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.KuPremiumRunnerWeb.model.Cakes;
import th.ac.ku.KuPremiumRunnerWeb.model.Cart;
import th.ac.ku.KuPremiumRunnerWeb.model.Certificate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class CertificateService {
    @Autowired
    private RestTemplate restTemplate;

    private List<Certificate> rings = new ArrayList<>();

    public List<Certificate> getAll(){
        String url = "http://localhost:8090/certificate";
        ResponseEntity<Certificate[]> response = restTemplate.getForEntity(url, Certificate[].class);
        Certificate[] certificates = response.getBody();
        return Arrays.asList(certificates);
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
    public void addCertificate(Certificate certificate){
        String url = "http://localhost:8090/certificate";
        restTemplate.postForObject(url, certificate, Certificate.class );
    }

    public Certificate getOneById(UUID id){
        String url = "http://localhost:8090/certificate/" + id;
        ResponseEntity<Certificate> response =
                restTemplate.getForEntity(url, Certificate.class);
        Certificate certificate = response.getBody();
        return certificate;
    }
    public void update(Certificate certificate){
        String url = "http://localhost:8090/certificate/" + certificate.getProdCertificateID();
        restTemplate.put(url, certificate, Certificate.class);
    }

    public void OrderConfig(){
        rings = this.getAll();
        for(int i = 0; i < this.getAll().size(); i++){
            String hee = new String("");
            //hee = String.valueOf(this.getAll().get(i).getId());
            Certificate ring = new Certificate(this.getAll().get(i).getProdCertificateID(),this.getAll().get(i).getProductName(),this.getAll().get(i).getProdCertificateName());
            rings.get(i).add(ring);
        }
    }

    public List<Certificate> getDummy(String name, String nameRing){ //getAll
        this.OrderConfig();
        List<Certificate> cart2 = new ArrayList<>();
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
//    public void delete(Certificate rings) {
//        String url = "http://localhost:8090/certificate/" + rings.getProdCertificateID();
//        restTemplate.delete(url, rings, Certificate.class);
//    }

}
