package th.ac.ku.KuPremiumRunnerWeb.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Research {

    private UUID relatedResearchID; //PK
    private String productName; //FK
    private String relatedResearchName;

    public Research(UUID relatedResearchID, String productName, String relatedResearchFilePath) {
        this.relatedResearchID = relatedResearchID;
        this.productName = productName;
        this.relatedResearchName = relatedResearchFilePath;
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

    public String getRelatedResearchName() {
        return relatedResearchName;
    }

    public void setRelatedResearchName(String relatedResearchName) {
        this.relatedResearchName = relatedResearchName;
    }

    public List<Research> getCartList() {
        return cartList;
    }

    public void setCartList(List<Research> cartList) {
        this.cartList = cartList;
    }

    private List<Research> cartList = new ArrayList<>();
    public void add(Research cartList){
        this.cartList.add(cartList);
    }
}
