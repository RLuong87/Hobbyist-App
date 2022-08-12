package com.hooked.app.payloads.response;

import com.hooked.app.models.angler.Angler;
import com.hooked.app.models.content.Content;

import java.util.List;

public class PublicAngler {
    private String avatar;
    private String name;
    private String status;
    private String birthday;
    private String location;
    private String about;
    private List<Content> content;

    public PublicAngler(String avatar, String name, String status, String birthday, String location, String about, List<Content> content) {
        this.avatar = avatar;
        this.name = name;
        this.status = status;
        this.birthday = birthday;
        this.location = location;
        this.about = about;
        this.content = content;
    }

    static public PublicAngler build(Angler angler) {
        return new PublicAngler(
                angler.getAvatar(),
                angler.getName(),
                angler.getStatus(),
                angler.getBirthday(),
                angler.getLocation(),
                angler.getAbout(),
                angler.getContent()
        );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }
}
