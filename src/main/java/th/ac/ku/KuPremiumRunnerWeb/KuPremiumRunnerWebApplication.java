package th.ac.ku.KuPremiumRunnerWeb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import th.ac.ku.KuPremiumRunnerWeb.storage.RelatedResearchStorageService;
import th.ac.ku.KuPremiumRunnerWeb.storage.StorageService;

@SpringBootApplication
public class KuPremiumRunnerWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(KuPremiumRunnerWebApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}
}
