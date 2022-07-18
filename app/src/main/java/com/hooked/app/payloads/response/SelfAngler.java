package com.hooked.app.payloads.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hooked.app.models.angler.Angler;

public class SelfAngler {

    @JsonIgnore
    private Long id;
    private String name;
    private String status;
    private String birthday;
    private String location;
    private String about;

    public SelfAngler(Long id, String name, String status, String birthday, String location, String about) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.birthday = birthday;
        this.location = location;
        this.about = about;
    }

    static public SelfAngler build(Angler angler) {
        return new SelfAngler(
                angler.getId(),
                angler.getName(),
                angler.getStatus(),
                angler.getBirthday(),
                angler.getLocation(),
                angler.getAbout()
        );
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
