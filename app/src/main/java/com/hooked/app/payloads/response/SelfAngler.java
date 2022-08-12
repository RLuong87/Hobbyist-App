package com.hooked.app.payloads.response;

import com.hooked.app.models.angler.Angler;

public class SelfAngler {

    private String avatar;
    private String name;
    private String status;
    private String birthday;
    private String location;
    private String about;

    public SelfAngler(String avatar, String name, String status, String birthday, String location, String about) {
        this.avatar = avatar;
        this.name = name;
        this.status = status;
        this.birthday = birthday;
        this.location = location;
        this.about = about;
    }

    static public SelfAngler build(Angler angler) {
        return new SelfAngler(
                angler.getAvatar(),
                angler.getName(),
                angler.getStatus(),
                angler.getBirthday(),
                angler.getLocation(),
                angler.getAbout()
        );
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
