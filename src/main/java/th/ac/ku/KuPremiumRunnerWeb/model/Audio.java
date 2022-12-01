package th.ac.ku.KuPremiumRunnerWeb.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Audio {
    private UUID prodAudioID; //PK
    private String productName; //FK
    private String prodAudioName;

    public Audio(UUID prodAudioID, String productName, String prodAudioName) {
        this.prodAudioID = prodAudioID;
        this.productName = productName;
        this.prodAudioName = prodAudioName;
    }

    public UUID getProdAudioID() {
        return prodAudioID;
    }

    public void setProdAudioID(UUID prodAudioID) {
        this.prodAudioID = prodAudioID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProdAudioName() {
        return prodAudioName;
    }

    public void setProdAudioName(String prodAudioName) {
        this.prodAudioName = prodAudioName;
    }

    

    public List<Audio> getCartList() {
        return cartList;
    }


    public void setCartList(List<Audio> cartList) {
        this.cartList = cartList;
    }

    private List<Audio> cartList = new ArrayList<>();


    public void add(Audio cartList){
        this.cartList.add(cartList);
    }
}
