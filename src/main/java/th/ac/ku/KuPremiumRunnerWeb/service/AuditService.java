package th.ac.ku.KuPremiumRunnerWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.KuPremiumRunnerWeb.model.Audit;
import th.ac.ku.KuPremiumRunnerWeb.model.Inspection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class AuditService {

    @Autowired
    private RestTemplate restTemplate;

    private List<Audit> rings = new ArrayList<>();

    public List<Audit> getAll(){
        String url = "http://localhost:8090/audit";
        ResponseEntity<Audit[]> response = restTemplate.getForEntity(url, Audit[].class);
        Audit[] audits = response.getBody();
        return Arrays.asList(audits);
    }

    public void addAudit(Audit audit){
        String url = "http://localhost:8090/audit";
        restTemplate.postForObject(url, audit, Audit.class );
    }

    public Audit getOneById(UUID id){
        String url = "http://localhost:8090/audit/" + id;
        ResponseEntity<Audit> response =
                restTemplate.getForEntity(url, Audit.class);
        Audit audit = response.getBody();
        return audit;
    }
    public void update(Audit audit){
        String url = "http://localhost:8090/audit/" + audit.getAuditID();
        restTemplate.put(url, audit, Audit.class);
    }

    public void OrderConfig(){
        rings = this.getAll();
        for(int i = 0; i < this.getAll().size(); i++){
            String hee = new String("");
            Audit ring = new Audit(this.getAll().get(i).getAuditID(),this.getAll().get(i).getProductName()
                    , this.getAll().get(i).getFda_356_Att(), this.getAll().get(i).getFda_356_Res(), this.getAll().get(i).getFda_356_Cer()
                    , this.getAll().get(i).getFda_414_Att(), this.getAll().get(i).getFda_414_Res(), this.getAll().get(i).getFda_414_Cer()
                    , this.getAll().get(i).getFda_416_Att(), this.getAll().get(i).getFda_416_Res(), this.getAll().get(i).getFda_416_Cer()
                    , this.getAll().get(i).getFda_418_Att(), this.getAll().get(i).getFda_418_Res(), this.getAll().get(i).getFda_418_Cer());
            rings.get(i).add(ring);
        }
    }

    public boolean checkNameAudit(String name){
        this.OrderConfig();
        List<Audit> cart2 = new ArrayList<>();
        for (int i =0; i<rings.size();i++) {
            if (name.equals(rings.get(i).getProductName())) {
                return false;
            }
        }
        return true;
    }

    public List<Audit> getDummy(String name, String nameRing){ //getAll
        this.OrderConfig();
        List<Audit> cart2 = new ArrayList<>();
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

    public void delete(Audit rings) {
        String url = "http://localhost:8090/audit/" + rings.getAuditID();
        restTemplate.delete(url, rings, Audit.class);
    }
}
