package th.ac.ku.cakeRunnerWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.cakeRunnerWeb.model.Cakes;
import th.ac.ku.cakeRunnerWeb.model.Cart;
import th.ac.ku.cakeRunnerWeb.model.Certificate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class CertificateService {
    @Autowired
    private RestTemplate restTemplate;

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
}
