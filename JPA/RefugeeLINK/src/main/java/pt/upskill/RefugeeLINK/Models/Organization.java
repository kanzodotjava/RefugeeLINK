//package pt.upskill.RefugeeLINK.Models;
//
//import jakarta.annotation.Nullable;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Null;
//
//import java.util.List;
//
//@Entity
//public class Organization {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String name;
//    private String address;
//    private String phoneNumber;
//    private String email;
//
//    private String password;
//
//    private String username;
//
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//
//    public Organization toLoginDto() {
//        Organization organization = new Organization();
//        organization.setPassword(this.getPassword());
//        organization.setUsername(this.getUsername());
//        return organization;
//    }
//}
