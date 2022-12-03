package th.ac.ku.KuPremiumRunnerWeb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FDA416 {

    private UUID fda416ID;
    private String productName;
    private String r_name;
    private double salla_s;
    private double salla_c;
    private String salla_f;
    private double sareus_s;
    private double sareus_c;
    private String sareus_f;

    public FDA416(UUID fda416ID, String productName, String r_name, double salla_s, double salla_c, String salla_f, double sareus_s, double sareus_c, String sareus_f) {
        this.fda416ID = fda416ID;
        this.productName = productName;
        this.r_name = r_name;
        this.salla_s = salla_s;
        this.salla_c = salla_c;
        this.salla_f = salla_f;
        this.sareus_s = sareus_s;
        this.sareus_c = sareus_c;
        this.sareus_f = sareus_f;
    }

    public UUID getFda416ID() {
        return fda416ID;
    }

    public void setFda416ID(UUID fda416ID) {
        this.fda416ID = fda416ID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public double getSalla_s() {
        return salla_s;
    }

    public void setSalla_s(double salla_s) {
        this.salla_s = salla_s;
    }

    public double getSalla_c() {
        return salla_c;
    }

    public void setSalla_c(double salla_c) {
        this.salla_c = salla_c;
    }

    public String getSalla_f() {
        return salla_f;
    }

    public void setSalla_f(String salla_f) {
        this.salla_f = salla_f;
    }

    public double getSareus_s() {
        return sareus_s;
    }

    public void setSareus_s(double sareus_s) {
        this.sareus_s = sareus_s;
    }

    public double getSareus_c() {
        return sareus_c;
    }

    public void setSareus_c(double sareus_c) {
        this.sareus_c = sareus_c;
    }

    public String getSareus_f() {
        return sareus_f;
    }

    public void setSareus_f(String sareus_f) {
        this.sareus_f = sareus_f;
    }

    public List<FDA416> getCartList() {
        return cartList;
    }

    public void setCartList(List<FDA416> cartList) {
        this.cartList = cartList;
    }

    private List<FDA416> cartList = new ArrayList<>();

    public void add(FDA416 cartList){
        this.cartList.add(cartList);
    }
}
