package com.hooked.app.payloads.response;

import com.hooked.app.models.angler.Angler;
import com.hooked.app.models.avatar.Avatar;

public class PublicAngler {
    private Long id;
    private Avatar avatar;
    private String name;
    private String location;
    private String about;

    public PublicAngler(Long id, Avatar avatar, String name, String location, String about) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.location = location;
        this.about = about;
    }

    static public PublicAngler build(Angler angler) {
        return new PublicAngler(
                angler.getId(),
                angler.getAvatar(),
                angler.getName(),
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

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
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
}
