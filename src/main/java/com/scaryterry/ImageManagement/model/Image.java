package com.scaryterry.ImageManagement.model;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
@Entity
@Table(name = "IMAGE")
public class Image {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "Title", length = 64, nullable =true)
    private String Title;
    @Column(name = "Description", nullable = true)
    private String Description;
    @Temporal(TemporalType.DATE)
    @Column(name = "Publishing_Date", nullable = true)
    private Date PublishingDate;
    @Column(name = "User_ID", nullable = true)
    private int UID;
    @JsonProperty("Base64")
    private String fileExtension;

    public Image() {
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public Image(String title, String description, Date publishingDate, int UID) {
        Title = title;
        Description = description;
        PublishingDate = publishingDate;
        this.UID = UID;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", PublishingDate=" + PublishingDate +
                ", UID=" + UID +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }



    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
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
}
