package com.hooked.app.models;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.hooked.app.models.auth.User;

import javax.persistence.*;

@Entity
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonIncludeProperties({"id"})
    private Customer customer;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Content() {
    }

    public Content(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

        public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}