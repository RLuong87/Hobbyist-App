package com.hooked.app.models;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.hooked.app.models.auth.User;

import javax.persistence.*;

@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String about;
    private String headline;
    private String location;
    private String birthday;

    @OneToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    @JsonIncludeProperties({"name"})
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    public Profile(String about, String headline, String location, String birthday, Customer customer) {
        this.about = about;
        this.headline = headline;
        this.location = location;
        this.birthday = birthday;
        this.customer = customer;
    }

    public Profile(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


}
