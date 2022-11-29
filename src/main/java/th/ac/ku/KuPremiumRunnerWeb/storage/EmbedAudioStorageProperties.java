package th.ac.ku.KuPremiumRunnerWeb.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("ea-storage")
public class EmbedAudioStorageProperties {

	private String location = "../kuPremium RunnerWeb/src/main/resources/uploaded file/Embed Audio";

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
