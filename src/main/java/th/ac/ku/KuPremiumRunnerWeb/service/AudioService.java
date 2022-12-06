package th.ac.ku.KuPremiumRunnerWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.KuPremiumRunnerWeb.model.Audio;
import th.ac.ku.KuPremiumRunnerWeb.model.Research;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class AudioService {
    @Autowired
    private RestTemplate restTemplate;

    private List<Audio> rings = new ArrayList<>();

    public List<Audio> getAll(){
        String url = "http://localhost:8090/audio";
        ResponseEntity<Audio[]> response = restTemplate.getForEntity(url, Audio[].class);
        Audio[] audios = response.getBody();
        return Arrays.asList(audios);
    }

    public void addAudio(Audio audio){
        String url = "http://localhost:8090/audio";
        restTemplate.postForObject(url, audio, Audio.class );
    }

    public Audio getOneById(UUID id){
        String url = "http://localhost:8090/audio/" + id;
        ResponseEntity<Audio> response =
                restTemplate.getForEntity(url, Audio.class);
        Audio audio = response.getBody();
        return audio;
    }

    public void update(Audio audio){
        String url = "http://localhost:8090/audio/" + audio.getProdAudioID();
        restTemplate.put(url, audio, Audio.class);
    }

    public void OrderConfig(){
        rings = this.getAll();
        for(int i = 0; i < this.getAll().size(); i++){
            String hee = new String("");
            Audio ring = new Audio(this.getAll().get(i).getProdAudioID(),this.getAll().get(i).getProductName(),this.getAll().get(i).getProdAudioName());
            rings.get(i).add(ring);
        }
    }

    public List<Audio> getDummy(String name, String nameRing){ //getAll
        this.OrderConfig();
        List<Audio> cart2 = new ArrayList<>();
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

    public void delete(Audio rings) {
        String url = "http://localhost:8090/audio/" + rings.getProdAudioID();
        restTemplate.delete(url, rings, Audio.class);
    }
}
