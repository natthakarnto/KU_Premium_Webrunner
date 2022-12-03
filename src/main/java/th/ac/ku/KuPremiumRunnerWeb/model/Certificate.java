package th.ac.ku.KuPremiumRunnerWeb.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Certificate {

    private UUID prodCertificateID; //PK
    private String productName; //FK
    private String prodCertificateName;

    public Certificate(UUID prodCertificateID, String productName, String prodCertificateName) {
        this.prodCertificateID = prodCertificateID;
        this.productName = productName;
        this.prodCertificateName = prodCertificateName;
    }

    public UUID getProdCertificateID() {
        return prodCertificateID;
    }

    public void setProdCertificateID(UUID prodCertificateID) {
        this.prodCertificateID = prodCertificateID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProdCertificateName() {
        return prodCertificateName;
    }

    public void setProdCertificateName(String prodCertificateName) {
        this.prodCertificateName = prodCertificateName;
    }

    private List<Certificate> cartList = new ArrayList<>();

    public void add(Certificate cartList){
        this.cartList.add(cartList);
    }

}
