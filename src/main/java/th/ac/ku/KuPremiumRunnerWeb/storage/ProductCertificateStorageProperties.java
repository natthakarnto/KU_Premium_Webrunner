package th.ac.ku.KuPremiumRunnerWeb.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("pc-storage")
public class ProductCertificateStorageProperties {

	private String location = "../kuPremium RunnerWeb/src/main/resources/uploaded file/Product Certificate";

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
