package th.ac.ku.KuPremiumRunnerWeb.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Audit {

    private UUID auditID;
    private String productName;
    private String fda_356_Att;
    private String fda_356_Res;
    private String fda_356_Cer;
    private String fda_414_Att;
    private String fda_414_Res;
    private String fda_414_Cer;
    private String fda_416_Att;
    private String fda_416_Res;
    private String fda_416_Cer;
    private String fda_418_Att;
    private String fda_418_Res;
    private String fda_418_Cer;

    public Audit(UUID auditID, String productName, String fda_356_Att, String fda_356_Res, String fda_356_Cer, String fda_414_Att, String fda_414_Res, String fda_414_Cer, String fda_416_Att, String fda_416_Res, String fda_416_Cer, String fda_418_Att, String fda_418_Res, String fda_418_Cer) {
        this.auditID = auditID;
        this.productName = productName;
        this.fda_356_Att = fda_356_Att;
        this.fda_356_Res = fda_356_Res;
        this.fda_356_Cer = fda_356_Cer;
        this.fda_414_Att = fda_414_Att;
        this.fda_414_Res = fda_414_Res;
        this.fda_414_Cer = fda_414_Cer;
        this.fda_416_Att = fda_416_Att;
        this.fda_416_Res = fda_416_Res;
        this.fda_416_Cer = fda_416_Cer;
        this.fda_418_Att = fda_418_Att;
        this.fda_418_Res = fda_418_Res;
        this.fda_418_Cer = fda_418_Cer;
    }

    public UUID getAuditID() {
        return auditID;
    }

    public void setAuditID(UUID auditID) {
        this.auditID = auditID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getFda_356_Att() {
        return fda_356_Att;
    }

    public void setFda_356_Att(String fda_356_Att) {
        this.fda_356_Att = fda_356_Att;
    }

    public String getFda_356_Res() {
        return fda_356_Res;
    }

    public void setFda_356_Res(String fda_356_Res) {
        this.fda_356_Res = fda_356_Res;
    }

    public String getFda_356_Cer() {
        return fda_356_Cer;
    }

    public void setFda_356_Cer(String fda_356_Cer) {
        this.fda_356_Cer = fda_356_Cer;
    }

    public String getFda_414_Att() {
        return fda_414_Att;
    }

    public void setFda_414_Att(String fda_414_Att) {
        this.fda_414_Att = fda_414_Att;
    }

    public String getFda_414_Res() {
        return fda_414_Res;
    }

    public void setFda_414_Res(String fda_414_Res) {
        this.fda_414_Res = fda_414_Res;
    }

    public String getFda_414_Cer() {
        return fda_414_Cer;
    }

    public void setFda_414_Cer(String fda_414_Cer) {
        this.fda_414_Cer = fda_414_Cer;
    }

    public String getFda_416_Att() {
        return fda_416_Att;
    }

    public void setFda_416_Att(String fda_416_Att) {
        this.fda_416_Att = fda_416_Att;
    }

    public String getFda_416_Res() {
        return fda_416_Res;
    }

    public void setFda_416_Res(String fda_416_Res) {
        this.fda_416_Res = fda_416_Res;
    }

    public String getFda_416_Cer() {
        return fda_416_Cer;
    }

    public void setFda_416_Cer(String fda_416_Cer) {
        this.fda_416_Cer = fda_416_Cer;
    }

    public String getFda_418_Att() {
        return fda_418_Att;
    }

    public void setFda_418_Att(String fda_418_Att) {
        this.fda_418_Att = fda_418_Att;
    }

    public String getFda_418_Res() {
        return fda_418_Res;
    }

    public void setFda_418_Res(String fda_418_Res) {
        this.fda_418_Res = fda_418_Res;
    }

    public String getFda_418_Cer() {
        return fda_418_Cer;
    }

    public void setFda_418_Cer(String fda_418_Cer) {
        this.fda_418_Cer = fda_418_Cer;
    }

    private List<Audit> cartList = new ArrayList<>();

    public void add(Audit cartList){
        this.cartList.add(cartList);
    }
}
