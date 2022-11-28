package th.ac.ku.KuPremiumRunnerWeb.model;

import java.util.UUID;

public class Certificate {

    private UUID prodCertificateID; //PK
    private String pID; //FK
    private String prodCertificateName;

    public String getpID() {
        return pID;
    }

    public void setpID(String pID) {
        this.pID = pID;
    }

    public UUID getProdCertificateID() {
        return prodCertificateID;
    }

    public void setProdCertificateID(UUID prodCertificateID) {
        this.prodCertificateID = prodCertificateID;
    }

    public String getProdCertificateName() {
        return prodCertificateName;
    }

    public void setProdCertificateName(String prodCertificateName) {
        this.prodCertificateName = prodCertificateName;
    }
}
