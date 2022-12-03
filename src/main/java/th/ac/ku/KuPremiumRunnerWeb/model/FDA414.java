package th.ac.ku.KuPremiumRunnerWeb.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FDA414 {

    private UUID fda414ID;
    private String productName;
    private String r_name;
    private double lead_s;
    private double lead_c;
    private String lead_f;

    public FDA414(UUID fda414ID, String productName, String r_name, double lead_s, double lead_c, String lead_f) {
        this.fda414ID = fda414ID;
        this.productName = productName;
        this.r_name = r_name;
        this.lead_s = lead_s;
        this.lead_c = lead_c;
        this.lead_f = lead_f;
    }

    public UUID getFda414ID() {
        return fda414ID;
    }

    public void setFda414ID(UUID fda414ID) {
        this.fda414ID = fda414ID;
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

    public double getLead_s() {
        return lead_s;
    }

    public void setLead_s(double lead_s) {
        this.lead_s = lead_s;
    }

    public double getLead_c() {
        return lead_c;
    }

    public void setLead_c(double lead_c) {
        this.lead_c = lead_c;
    }

    public String getLead_f() {
        return lead_f;
    }

    public void setLead_f(String lead_f) {
        this.lead_f = lead_f;
    }

    private List<FDA414> cartList = new ArrayList<>();

    public void add(FDA414 cartList){
        this.cartList.add(cartList);
    }
}
