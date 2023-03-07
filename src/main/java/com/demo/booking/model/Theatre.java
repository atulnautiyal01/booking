package com.demo.booking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Document(collection = "theatre")
public class Theatre {
    @Id
    private String id;

    @NotBlank
    @Size(max = 35)
    private String name;

    @NotBlank
    @Size(max = 10)
    private String phone;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private String userID;

    @NotNull
    private double latitude;

    @NotNull
    private double longitude;

    private String city;

    private String state;

    private String status;

    private boolean active;

    public Theatre() {
    }

    public Theatre(String id, @NotBlank @Size(max = 35) String name, @NotBlank @Size(max = 10) String phone, @NotBlank @Size(max = 50) @Email String email, String userID, @NotBlank @Size(max = 120) double latitude, @NotBlank @Size(max = 120) double longitude, String city, String state, String status, boolean active) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.userID = userID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.state = state;
        this.status = status;
        this.active = active;
    }

    @Override
    public String toString() {
        return "Theatre{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", userID='" + userID + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", status='" + status + '\'' +
                ", active=" + active +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
