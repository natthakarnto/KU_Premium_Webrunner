package th.ac.ku.KuPremiumRunnerWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.KuPremiumRunnerWeb.model.Admin;
import th.ac.ku.KuPremiumRunnerWeb.model.User;

import java.util.Arrays;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private RestTemplate restTemplate;

    private Admin admin;
    public List<Admin> getAll(){
        String url = "http://localhost:8090/admin";
        ResponseEntity<Admin[]> response = restTemplate.getForEntity(url, Admin[].class);
        Admin[] admins = response.getBody();
        return Arrays.asList(admins);
    }

    public void addAdmin(Admin admin){
        String url = "http://localhost:8090/admin";
        restTemplate.postForObject(url, admin, Admin.class);
    }

    public void setLoginAdmin(String name){
        for(int i = 0 ; i <this.getAll().size();i++){
            if(name.equals(this.getAll().get(i).getUsername())){
                admin = this.getAll().get(i);
            }
        }
    }
    public Admin getUser(){
        return admin;
    }
}
