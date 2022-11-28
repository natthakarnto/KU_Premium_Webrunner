package th.ac.ku.KuPremiumRunnerWeb.model;

import java.util.UUID;

public class Admin {
    private UUID admin_id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String telNo;
    private String email;
    private String researcherRank;

    public UUID getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(UUID admin_id) {
        this.admin_id = admin_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResearcherRank() {
        return researcherRank;
    }

    public void setResearcherRank(String researcherRank) {
        this.researcherRank = researcherRank;
    }
}
