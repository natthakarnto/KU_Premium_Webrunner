package th.ac.ku.KuPremiumRunnerWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.KuPremiumRunnerWeb.model.Research;
import th.ac.ku.KuPremiumRunnerWeb.model.Story;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class StoryService {
    @Autowired
    private RestTemplate restTemplate;

    private List<Story> rings = new ArrayList<>();

    public List<Story> getAll(){
        String url = "http://localhost:8090/story";
        ResponseEntity<Story[]> response = restTemplate.getForEntity(url, Story[].class);
        Story[] storys = response.getBody();
        return Arrays.asList(storys);
    }

    public void addStory(Story story){
        String url = "http://localhost:8090/story";
        restTemplate.postForObject(url, story, Story.class );
    }

    public Story getOneById(UUID id){
        String url = "http://localhost:8090/story/" + id;
        ResponseEntity<Story> response =
                restTemplate.getForEntity(url, Story.class);
        Story story = response.getBody();
        return story;
    }

    public void update(Story story){
        String url = "http://localhost:8090/story/" + story.getProdStoryID();
        restTemplate.put(url, story, Story.class);
    }

    public void OrderConfig(){
        rings = this.getAll();
        for(int i = 0; i < this.getAll().size(); i++){
            String hee = new String("");
            Story ring = new Story(this.getAll().get(i).getProdStoryID(),this.getAll().get(i).getProductName(),this.getAll().get(i).getProdStoryName());
            rings.get(i).add(ring);
        }
    }

    public List<Story> getDummy(String name, String nameRing){ //getAll
        this.OrderConfig();
        List<Story> cart2 = new ArrayList<>();
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
    public void delete(Story rings) {
        String url = "http://localhost:8090/story/" + rings.getProdStoryID();
        restTemplate.delete(url, rings, Story.class);
    }

}
