package com.hooked.app.models.angler;


import com.hooked.app.models.auth.User;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createdDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "second_user_id", referencedColumnName = "id")
    private User secondUser;

    public Friend() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getSecondUser() {
        return secondUser;
    }

    public void setSecondUser(User secondUser) {
        this.secondUser = secondUser;
    }
}
