package com.demo.booking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Date;

@Document(collection = "movie")
public class Movie {
    @Id
    private String id;

    @NotBlank
    @Size(max = 20)
    private String name;

    private String description;

    private double duration;

    private String genre;

    private String language;

    private Date releaseDate;

    private String poster;

    private Date date;

    private String status;

    private boolean active;

    public Movie() {
    }

    public Movie(String id, @NotBlank @Size(max = 20) String name, String description, double duration, String genre, String language, Timestamp releaseDate, String poster, Timestamp date, String status, boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.genre = genre;
        this.language = language;
        this.releaseDate = releaseDate;
        this.poster = poster;
        this.date = date;
        this.status = status;
        this.active = active;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
