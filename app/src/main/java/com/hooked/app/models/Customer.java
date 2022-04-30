package com.hooked.app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hooked.app.models.auth.User;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "customer")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Profile profile;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Content> content;

    @OneToOne
    @JoinColumn(
            name = "users_id",
            referencedColumnName = "id"
    )
    @JsonIgnore
    private User user;

    public Customer() {

    }

    public Customer(String name, String email, Profile profile, List<Content> content, User user) {
        this.name = name;
        this.email = email;
        this.profile = profile;
        this.content = content;
        this.user = user;
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

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
