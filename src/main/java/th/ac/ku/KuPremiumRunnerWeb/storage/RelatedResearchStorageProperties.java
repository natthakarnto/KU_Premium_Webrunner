package th.ac.ku.KuPremiumRunnerWeb.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("rr-storage")
public class RelatedResearchStorageProperties {

	private String location = "../kuPremium RunnerWeb/src/main/resources/uploaded file/Related Research";

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
