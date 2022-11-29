package th.ac.ku.KuPremiumRunnerWeb.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("psev-storage")
public class ProductStoryEmbedVideoStorageProperties {

	private String location = "../kuPremium RunnerWeb/src/main/resources/uploaded file/Product Story Embed Video";

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
