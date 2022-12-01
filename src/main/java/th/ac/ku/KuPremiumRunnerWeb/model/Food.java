package th.ac.ku.KuPremiumRunnerWeb.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Food {

    private UUID prodFoodID; //PK
    private String productName; //FK
    private String prodFoodName;

    public Food(UUID prodFoodID, String productName, String prodFoodName) {
        this.prodFoodID = prodFoodID;
        this.productName = productName;
        this.prodFoodName = prodFoodName;
    }

    public UUID getProdFoodID() {
        return prodFoodID;
    }

    public void setProdFoodID(UUID prodFoodID) {
        this.prodFoodID = prodFoodID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProdFoodName() {
        return prodFoodName;
    }

    public void setProdFoodName(String prodFoodName) {
        this.prodFoodName = prodFoodName;
    }


    public List<Food> getCartList() {
        return cartList;
    }


    public void setCartList(List<Food> cartList) {
        this.cartList = cartList;
    }

    private List<Food> cartList = new ArrayList<>();


    public void add(Food cartList){
        this.cartList.add(cartList);
    }
}
