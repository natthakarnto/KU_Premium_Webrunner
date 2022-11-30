package th.ac.ku.KuPremiumRunnerWeb.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Story {

    private UUID prodStoryID; //PK
    private String productName; //FK
    private String prodStoryName;

    public Story(UUID prodStoryID, String productName, String prodStoryName) {
        this.prodStoryID = prodStoryID;
        this.productName = productName;
        this.prodStoryName = prodStoryName;
    }

    public UUID getProdStoryID() {
        return prodStoryID;
    }

    public void setProdStoryID(UUID prodStoryID) {
        this.prodStoryID = prodStoryID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProdStoryName() {
        return prodStoryName;
    }

    public void setProdStoryName(String prodStoryName) {
        this.prodStoryName = prodStoryName;
    }


    public List<Story> getCartList() {
        return cartList;
    }


    public void setCartList(List<Story> cartList) {
        this.cartList = cartList;
    }

    private List<Story> cartList = new ArrayList<>();


    public void add(Story cartList){
        this.cartList.add(cartList);
    }

}
