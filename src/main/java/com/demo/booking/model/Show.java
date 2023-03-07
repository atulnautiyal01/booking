package com.demo.booking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Document(collection = "show")
public class Show {
    @Id
    private String id;

    @NotBlank
    private String theatreID;

    @NotBlank
    private String movieID;

    @NotNull
    private Date date;

    @NotNull
    private Integer showStartTime;

    @NotNull
    private Integer showEndTime;

    @NotNull
    private double ticketPrice;

    @NotNull
    private int totalSeats;

    private int[] selectedSeats;

    private int[] bookedSeats;

    @NotNull
    private boolean active;

    public Show() {
    }

    public Show(String id, @NotBlank String theatreID, @NotBlank String movieID, @NotBlank Date date, @NotBlank Integer showStartTime, @NotBlank Integer showEndTime, @NotBlank double ticketPrice, @NotBlank int totalSeats, int[] selectedSeats, int[] bookedSeats, @NotBlank boolean active) {
        this.id = id;
        this.theatreID = theatreID;
        this.movieID = movieID;
        this.date = date;
        this.showStartTime = showStartTime;
        this.showEndTime = showEndTime;
        this.ticketPrice = ticketPrice;
        this.totalSeats = totalSeats;
        this.selectedSeats = selectedSeats;
        this.bookedSeats = bookedSeats;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTheatreID() {
        return theatreID;
    }

    public void setTheatreID(String theatreID) {
        this.theatreID = theatreID;
    }

    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getShowStartTime() {
        return showStartTime;
    }

    public void setShowStartTime(Integer showStartTime) {
        this.showStartTime = showStartTime;
    }

    public Integer getShowEndTime() {
        return showEndTime;
    }

    public void setShowEndTime(Integer showEndTime) {
        this.showEndTime = showEndTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int[] getSelectedSeats() {
        return selectedSeats;
    }

    public void setSelectedSeats(int[] selectedSeats) {
        this.selectedSeats = selectedSeats;
    }

    public int[] getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(int[] bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
