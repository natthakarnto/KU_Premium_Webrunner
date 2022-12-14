package th.ac.ku.KuPremiumRunnerWeb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Order {

    private UUID orderId;
//    private Date date;
    private String date;
    private String name;
    private String surname;
    private String mobile;
    private String address;
    private String cakes;
    private List<Cart> cartList = new ArrayList<>();
    private String username;
    private String status;
//    private Date approvedDate;
    private String message;

    private String approvedDate;

//    private LocalDateTime approvedDate;

    public void add(Cart cartList){
        this.cartList.add(cartList);
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

//    public LocalDateTime getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDateTime date) {
//        this.date = date;
//    }


//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCakes() {
        return cakes;
    }

    public void setCakes(String cakes) {
        this.cakes = cakes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public LocalDateTime getApprovedDate() {
//        return approvedDate;
//    }
//
//    public void setApprovedDate(LocalDateTime approvedDate) {
//        this.approvedDate = approvedDate;
//    }

//    public Date getApprovedDate() {
//        return approvedDate;
//    }
//
//    public void setApprovedDate(Date approvedDate) {
//        this.approvedDate = approvedDate;
//    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(String approvedDate) {
        this.approvedDate = approvedDate;
    }
}
