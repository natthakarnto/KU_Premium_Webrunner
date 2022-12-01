package th.ac.ku.KuPremiumRunnerWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.KuPremiumRunnerWeb.model.Food;
import th.ac.ku.KuPremiumRunnerWeb.model.Story;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class FoodService {
    @Autowired
    private RestTemplate restTemplate;

    private List<Food> rings = new ArrayList<>();

    public List<Food> getAll(){
        String url = "http://localhost:8090/food";
        ResponseEntity<Food[]> response = restTemplate.getForEntity(url, Food[].class);
        Food[] foods = response.getBody();
        return Arrays.asList(foods);
    }

    public void addStory(Food food){
        String url = "http://localhost:8090/food";
        restTemplate.postForObject(url, food, Food.class );
    }

    public Food getOneById(UUID id){
        String url = "http://localhost:8090/food/" + id;
        ResponseEntity<Food> response =
                restTemplate.getForEntity(url, Food.class);
        Food food = response.getBody();
        return food;
    }

    public void update(Food food){
        String url = "http://localhost:8090/food/" + food.getProdFoodID();
        restTemplate.put(url, food, Food.class);
    }

    public void OrderConfig(){
        rings = this.getAll();
        for(int i = 0; i < this.getAll().size(); i++){
            String hee = new String("");
            Food ring = new Food(this.getAll().get(i).getProdFoodID(),this.getAll().get(i).getProductName(),this.getAll().get(i).getProdFoodName());
            rings.get(i).add(ring);
        }
    }

    public List<Food> getDummy(String name, String nameRing){ //getAll
        this.OrderConfig();
        List<Food> cart2 = new ArrayList<>();
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
}
