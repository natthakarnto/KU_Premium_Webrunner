package th.ac.ku.KuPremiumRunnerWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import th.ac.ku.KuPremiumRunnerWeb.model.Audio;
import th.ac.ku.KuPremiumRunnerWeb.model.Research;
import th.ac.ku.KuPremiumRunnerWeb.service.AudioService;
import th.ac.ku.KuPremiumRunnerWeb.service.CakesService;
import th.ac.ku.KuPremiumRunnerWeb.service.UserService;

import java.util.UUID;

@Controller
@RequestMapping("/audio")
public class AudioController {
    @Autowired
    private AudioService audioService;

    @Autowired
    private CakesService cakesService;
    @Autowired
    private UserService userServices;

    @GetMapping("/edit/{id}")
    public String getEditForm(@PathVariable UUID id, Model model) {
        Audio audio = audioService.getOneById(id);
        model.addAttribute("audio", audio);
        return "audio-edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Audio audio, Model model, RedirectAttributes redirectAttrs) {
        if(checkAddress(audio.getProdAudioName(), audio.getProductName())) {
            audioService.update(audio);
            return "redirect:/audio/list";
        }
        else {
            redirectAttrs.addFlashAttribute("error","Please fill all the information fields!");
            return "redirect:/audio/list";
        }
    }

    @GetMapping("/list") //getAll
    public String getListForm(Model model, Authentication authentication) {
        model.addAttribute("rings6", audioService.getAll());
        return "audio-list";
    }

//    @GetMapping("/list/{id}") //getDummy
//    public String getListIDForm(@PathVariable UUID id, Model model, Authentication authentication) {
//        Story rings = storyService.getOneById(id);
//        model.addAttribute("rings4", storyService.getDummy(authentication.getName() ,rings.getProductName()));
//        return "story-list";
//    }

    @GetMapping
    public String getCakes(Model model, Authentication authentication) {
        userServices.setLoginUserCakes(authentication.getName());
        model.addAttribute("audio", audioService.getAll());
        return "audio-edit";
    }

    @GetMapping("/add")
    public String getAddForm(Model model){
        model.addAttribute("audio", audioService.getAll());
        return "audio-add";
    }

    @PostMapping("/add")
    public String addAudio(@ModelAttribute Audio audio, Model model, RedirectAttributes redirectAttrs) {
        // พอรับเข้ามาจะเอาเข้า List
        if(checkAddress(audio.getProdAudioName(), audio.getProductName())) {
            audioService.addAudio(audio);
            return "redirect:/audio/list";
        }
        else {
            redirectAttrs.addFlashAttribute("error","Please fill all the information fields!");
            return "redirect:/audio/add";
        }
    }

    public boolean checkAddress(String prodAudioName, String productName){
        if (prodAudioName.equals("") || (productName.equals(""))){
            return false;
        }return true;
    }

    @GetMapping("/remove/{id}")
    public String removeAudio(@PathVariable UUID id, Model model,Authentication authentication){
        Audio set = audioService.getOneById(id);
        audioService.delete(set);
        return "redirect:/audio/list";
    }
}
