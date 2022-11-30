package th.ac.ku.KuPremiumRunnerWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.KuPremiumRunnerWeb.model.Cakes;
import th.ac.ku.KuPremiumRunnerWeb.model.User;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    private User user;

    private Cakes cakes;

    public List<User> getAll(){
        String url = "http://localhost:8090/user";
        ResponseEntity<User[]> response = restTemplate.getForEntity(url, User[].class);
        User[] users = response.getBody();
        return Arrays.asList(users);
    }

    public List<Cakes> getAllCakes(){
        String url = "http://localhost:8090/cakes";
        ResponseEntity<Cakes[]> response = restTemplate.getForEntity(url, Cakes[].class);
        Cakes[] cakes = response.getBody();
        return Arrays.asList(cakes);
    }

    public void addUser(User user){
        String url = "http://localhost:8090/user";
        restTemplate.postForObject(url, user, User.class);
    }

    public void setLoginUser(String name){
        for(int i = 0 ; i <this.getAll().size();i++){
            if(name.equals(this.getAll().get(i).getUsername())){
                user = this.getAll().get(i);
            }
        }
    }

    public void setLoginUserCakes(String name){
        for(int i = 0 ; i <this.getAllCakes().size();i++){
            if(name.equals(this.getAllCakes().get(i).getProductName())){
                System.out.println(this.getAllCakes().get(i).getProductName());
                cakes = this.getAllCakes().get(i);
            }
        }
    }

    public User getUser(){
        return user;
    }


}
