package th.ac.ku.KuPremiumRunnerWeb.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Research {

    private UUID relatedResearchID; //PK
    private String productName; //FK
    private String relatedResearchFilePath;

    public Research(UUID relatedResearchID, String productName, String relatedResearchFilePath) {
        this.relatedResearchID = relatedResearchID;
        this.productName = productName;
        this.relatedResearchFilePath = relatedResearchFilePath;
    }

    public UUID getRelatedResearchID() {
        return relatedResearchID;
    }

    public void setRelatedResearchID(UUID relatedResearchID) {
        this.relatedResearchID = relatedResearchID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getRelatedResearchFilePath() {
        return relatedResearchFilePath;
    }

    public void setRelatedResearchFilePath(String relatedResearchFilePath) {
        this.relatedResearchFilePath = relatedResearchFilePath;
    }

    private List<Research> cartList = new ArrayList<>();
    public void add(Research cartList){
        this.cartList.add(cartList);
    }
}
