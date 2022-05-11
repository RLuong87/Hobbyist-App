package com.hooked.app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hooked.app.models.auth.User;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String status;
    private String birthday;
    private String location;
    private String about;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Content> content;

    @OneToOne
    private Avatar avatar;

    @OneToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    public Customer() {

    }

    public Customer(String name, String status, String birthday, String location, String about) {
        this.name = name;
        this.status = status;
        this.birthday = birthday;
        this.location = location;
        this.about = about;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
