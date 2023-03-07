package com.demo.booking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Document(collection = "booking")
public class Booking {
    @Id
    private String id;

    @NotBlank
    private String userID;

    @NotBlank
    private  String showID;

    @NotNull
    private int[] seats;

    @NotBlank
    private String transactionID;

    @NotNull
    Date date;

    EnumBookingStatus enumBookingStatus;

    private boolean active;

    public Booking() {
    }

    public Booking(String id, @NotBlank String userID, @NotBlank String showID, @NotNull int[] seats, @NotBlank String transactionID, @NotNull Date date, EnumBookingStatus enumBookingStatus, boolean active) {
        this.id = id;
        this.userID = userID;
        this.showID = showID;
        this.seats = seats;
        this.transactionID = transactionID;
        this.date = date;
        this.enumBookingStatus = enumBookingStatus;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getShowID() {
        return showID;
    }

    public void setShowID(String showID) {
        this.showID = showID;
    }

    public int[] getSeats() {
        return seats;
    }

    public void setSeats(int[] seats) {
        this.seats = seats;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public EnumBookingStatus getEnumBookingStatus() {
        return enumBookingStatus;
    }

    public void setEnumBookingStatus(EnumBookingStatus enumBookingStatus) {
        this.enumBookingStatus = enumBookingStatus;
    }
}
