package th.ac.ku.KuPremiumRunnerWeb.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import th.ac.ku.KuPremiumRunnerWeb.storage.FileSystemStorageService;
import th.ac.ku.KuPremiumRunnerWeb.storage.StorageFileNotFoundException;

import java.io.IOException;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/upload_files")
public class UploadController {

    public UploadController(FileSystemStorageService storageService) {
        this.storageService = storageService;
    }
    private final FileSystemStorageService storageService;

    @GetMapping("/file")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                        path -> MvcUriComponentsBuilder.fromMethodName(UploadController.class,
                                "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/handle_upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You uploaded '"+ file.getOriginalFilename() + "' successfully!");

        return "redirect:/cakes/add";
    }

    @PostMapping("/certificate_upload")
    public String handleCertificateUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You uploaded '"+ file.getOriginalFilename() + "' successfully!");

        return "redirect:/certificate/add";
    }

    @PostMapping("/research_upload")
    public String handleResearchUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You uploaded '"+ file.getOriginalFilename() + "' successfully!");

        return "redirect:/research/add";
    }

    @PostMapping("/story_upload")
    public String handleStoryUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You uploaded '"+ file.getOriginalFilename() + "' successfully!");

        return "redirect:/story/add";
    }

    @PostMapping("/food_upload")
    public String handleFoodUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You uploaded '"+ file.getOriginalFilename() + "' successfully!");

        return "redirect:/food/add";
    }

    @PostMapping("/audio_upload")
    public String handleAudioUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You uploaded '"+ file.getOriginalFilename() + "' successfully!");

        return "redirect:/audio/add";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/upload")
    public String getUpload(Model model) {
        return "upload-test";
    }
}
