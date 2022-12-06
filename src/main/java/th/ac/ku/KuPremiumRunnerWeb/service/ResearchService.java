package th.ac.ku.KuPremiumRunnerWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.KuPremiumRunnerWeb.model.Certificate;
import th.ac.ku.KuPremiumRunnerWeb.model.Research;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class ResearchService {
    @Autowired
    private RestTemplate restTemplate;

    private List<Research> rings = new ArrayList<>();

    public List<Research> getAll(){
        String url = "http://localhost:8090/research";
        ResponseEntity<Research[]> response = restTemplate.getForEntity(url, Research[].class);
        Research[] researchs = response.getBody();
        return Arrays.asList(researchs);
    }

    public void addResearch(Research research){
        String url = "http://localhost:8090/research";
        restTemplate.postForObject(url, research, Research.class );
    }

    public Research getOneById(UUID id){
        String url = "http://localhost:8090/research/" + id;
        ResponseEntity<Research> response =
                restTemplate.getForEntity(url, Research.class);
        Research research = response.getBody();
        return research;
    }

    public void update(Research research){
        String url = "http://localhost:8090/research/" + research.getRelatedResearchID();
        restTemplate.put(url, research, Research.class);
    }

    public void OrderConfig(){
        rings = this.getAll();
        for(int i = 0; i < this.getAll().size(); i++){
            String hee = new String("");
            //hee = String.valueOf(this.getAll().get(i).getId());
            Research ring = new Research(this.getAll().get(i).getRelatedResearchID(),this.getAll().get(i).getProductName(),this.getAll().get(i).getRelatedResearchName());
            rings.get(i).add(ring);
        }
    }

    public List<Research> getDummy(String name, String nameRing){ //getAll
        this.OrderConfig();
        List<Research> cart2 = new ArrayList<>();
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

    public void delete(Research rings) {
        String url = "http://localhost:8090/research/" + rings.getRelatedResearchID();
        restTemplate.delete(url, rings, Research.class);
    }
}
