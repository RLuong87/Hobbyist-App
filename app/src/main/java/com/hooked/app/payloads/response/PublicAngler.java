package com.hooked.app.payloads.response;

import com.hooked.app.models.angler.Angler;
import com.hooked.app.models.avatar.Avatar;

public class PublicAngler {
    private Long id;
    private String name;
    private Avatar avatar;
    private String location;

    public PublicAngler(Long id, String name, Avatar avatar, String location) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.location = location;
    }

    static public PublicAngler build(Angler angler) {
        return new PublicAngler(
                angler.getId(),
                angler.getName(),
                angler.getAvatar(),
                angler.getLocation()
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
}
