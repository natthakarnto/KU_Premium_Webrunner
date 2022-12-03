package th.ac.ku.KuPremiumRunnerWeb.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FDA356 {

    private UUID fda356ID; //PK
    private String productName; //FK
    private String r_name; //FK
    private double ph_value_s;
    private double ph_value_c;
    private String ph_value_f;
    private double coli_s;
    private double coli_c;
    private String coli_f;
    private double yeast_mold_s;
    private double yeast_mold_r;
    private double yeast_c;
    private double mold_c;
    private String yeast_mold_f;

    public FDA356(UUID fda356ID, String productName, String r_name, double ph_value_s, double ph_value_c, String ph_value_f, double coli_s, double coli_c, String coli_f, double yeast_mold_s, double yeast_mold_r, double yeast_c, double mold_c, String yeast_mold_f) {
        this.fda356ID = fda356ID;
        this.productName = productName;
        this.r_name = r_name;
        this.ph_value_s = ph_value_s;
        this.ph_value_c = ph_value_c;
        this.ph_value_f = ph_value_f;
        this.coli_s = coli_s;
        this.coli_c = coli_c;
        this.coli_f = coli_f;
        this.yeast_mold_s = yeast_mold_s;
        this.yeast_mold_r = yeast_mold_r;
        this.yeast_c = yeast_c;
        this.mold_c = mold_c;
        this.yeast_mold_f = yeast_mold_f;
    }

    public UUID getFda356ID() {
        return fda356ID;
    }

    public void setFda356ID(UUID fda356ID) {
        this.fda356ID = fda356ID;
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

    public double getPh_value_s() {
        return ph_value_s;
    }

    public void setPh_value_s(double ph_value_s) {
        this.ph_value_s = ph_value_s;
    }

    public double getPh_value_c() {
        return ph_value_c;
    }

    public void setPh_value_c(double ph_value_c) {
        this.ph_value_c = ph_value_c;
    }

    public String getPh_value_f() {
        return ph_value_f;
    }

    public void setPh_value_f(String ph_value_f) {
        this.ph_value_f = ph_value_f;
    }

    public double getColi_s() {
        return coli_s;
    }

    public void setColi_s(double coli_s) {
        this.coli_s = coli_s;
    }

    public double getColi_c() {
        return coli_c;
    }

    public void setColi_c(double coli_c) {
        this.coli_c = coli_c;
    }

    public String getColi_f() {
        return coli_f;
    }

    public void setColi_f(String coli_f) {
        this.coli_f = coli_f;
    }

    public double getYeast_mold_s() {
        return yeast_mold_s;
    }

    public void setYeast_mold_s(double yeast_mold_s) {
        this.yeast_mold_s = yeast_mold_s;
    }

    public double getYeast_mold_r() {
        return yeast_mold_r;
    }

    public void setYeast_mold_r(double yeast_mold_r) {
        this.yeast_mold_r = yeast_mold_r;
    }

    public double getYeast_c() {
        return yeast_c;
    }

    public void setYeast_c(double yeast_c) {
        this.yeast_c = yeast_c;
    }

    public double getMold_c() {
        return mold_c;
    }

    public void setMold_c(double mold_c) {
        this.mold_c = mold_c;
    }

    public String getYeast_mold_f() {
        return yeast_mold_f;
    }

    public void setYeast_mold_f(String yeast_mold_f) {
        this.yeast_mold_f = yeast_mold_f;
    }

    public List<FDA356> getCartList() {
        return cartList;
    }

    public void setCartList(List<FDA356> cartList) {
        this.cartList = cartList;
    }

    private List<FDA356> cartList = new ArrayList<>();

    public void add(FDA356 cartList){
        this.cartList.add(cartList);
    }
}