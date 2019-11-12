package com.scaryterry.ImageManagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CANVA")
public class Canva {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "Title", length = 64, nullable =true)
    private String Title;
    @Column(name = "Description", nullable = true)
    private String Description;
    @Temporal(TemporalType.DATE)
    @Column(name = "Creation_Date", nullable =true)
    private Date PublishingDate;
    @Column(name = "User_ID", nullable = true)
    private int UID;

    @ManyToMany(
            cascade = CascadeType.ALL

    )
    private List<Image> images = new ArrayList<>();

    public Canva() {
    }

    public Canva(String title, String description, Date publishingDate, int UID) {
        Title = title;
        Description = description;
        PublishingDate = publishingDate;
        this.UID = UID;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getPublishingDate() {
        return PublishingDate;
    }

    public void setPublishingDate(Date publishingDate) {
        PublishingDate = publishingDate;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }
}
