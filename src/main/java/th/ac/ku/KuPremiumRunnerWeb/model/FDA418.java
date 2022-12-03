package th.ac.ku.KuPremiumRunnerWeb.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FDA418 {

    private UUID fda418ID;
    private String productName;
    private String r_name;
    private double sum_benzoic_sorbic;
    private double benzoic_c;
    private double sorbic_c;
    private String benzoic_sorbic_f;
    private double synt_s;
    private double synt_c;
    private String synt_f;
    private double sod_s;
    private double sod_c;
    private double sod_f;
    private double potas_s;
    private double potas_c;
    private String potas_f;
    private double plate_s;
    private double plate_c;
    private String plate_f;

    public FDA418(UUID fda418ID, String productName, String r_name, double sum_benzoic_sorbic, double benzoic_c, double sorbic_c, String benzoic_sorbic_f, double synt_s, double synt_c, String synt_f, double sod_s, double sod_c, double sod_f, double potas_s, double potas_c, String potas_f, double plate_s, double plate_c, String plate_f) {
        this.fda418ID = fda418ID;
        this.productName = productName;
        this.r_name = r_name;
        this.sum_benzoic_sorbic = sum_benzoic_sorbic;
        this.benzoic_c = benzoic_c;
        this.sorbic_c = sorbic_c;
        this.benzoic_sorbic_f = benzoic_sorbic_f;
        this.synt_s = synt_s;
        this.synt_c = synt_c;
        this.synt_f = synt_f;
        this.sod_s = sod_s;
        this.sod_c = sod_c;
        this.sod_f = sod_f;
        this.potas_s = potas_s;
        this.potas_c = potas_c;
        this.potas_f = potas_f;
        this.plate_s = plate_s;
        this.plate_c = plate_c;
        this.plate_f = plate_f;
    }

    public UUID getFda418ID() {
        return fda418ID;
    }

    public void setFda418ID(UUID fda418ID) {
        this.fda418ID = fda418ID;
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

    public double getSum_benzoic_sorbic() {
        return sum_benzoic_sorbic;
    }

    public void setSum_benzoic_sorbic(double sum_benzoic_sorbic) {
        this.sum_benzoic_sorbic = sum_benzoic_sorbic;
    }

    public double getBenzoic_c() {
        return benzoic_c;
    }

    public void setBenzoic_c(double benzoic_c) {
        this.benzoic_c = benzoic_c;
    }

    public double getSorbic_c() {
        return sorbic_c;
    }

    public void setSorbic_c(double sorbic_c) {
        this.sorbic_c = sorbic_c;
    }

    public String getBenzoic_sorbic_f() {
        return benzoic_sorbic_f;
    }

    public void setBenzoic_sorbic_f(String benzoic_sorbic_f) {
        this.benzoic_sorbic_f = benzoic_sorbic_f;
    }

    public double getSynt_s() {
        return synt_s;
    }

    public void setSynt_s(double synt_s) {
        this.synt_s = synt_s;
    }

    public double getSynt_c() {
        return synt_c;
    }

    public void setSynt_c(double synt_c) {
        this.synt_c = synt_c;
    }

    public String getSynt_f() {
        return synt_f;
    }

    public void setSynt_f(String synt_f) {
        this.synt_f = synt_f;
    }

    public double getSod_s() {
        return sod_s;
    }

    public void setSod_s(double sod_s) {
        this.sod_s = sod_s;
    }

    public double getSod_c() {
        return sod_c;
    }

    public void setSod_c(double sod_c) {
        this.sod_c = sod_c;
    }

    public double getSod_f() {
        return sod_f;
    }

    public void setSod_f(double sod_f) {
        this.sod_f = sod_f;
    }

    public double getPotas_s() {
        return potas_s;
    }

    public void setPotas_s(double potas_s) {
        this.potas_s = potas_s;
    }

    public double getPotas_c() {
        return potas_c;
    }

    public void setPotas_c(double potas_c) {
        this.potas_c = potas_c;
    }

    public String getPotas_f() {
        return potas_f;
    }

    public void setPotas_f(String potas_f) {
        this.potas_f = potas_f;
    }

    public double getPlate_s() {
        return plate_s;
    }

    public void setPlate_s(double plate_s) {
        this.plate_s = plate_s;
    }

    public double getPlate_c() {
        return plate_c;
    }

    public void setPlate_c(double plate_c) {
        this.plate_c = plate_c;
    }

    public String getPlate_f() {
        return plate_f;
    }

    public void setPlate_f(String plate_f) {
        this.plate_f = plate_f;
    }
}
