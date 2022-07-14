package com.hooked.app.payloads.response;

import com.hooked.app.models.angler.Angler;

import java.util.Set;

public class SelfAngler {

    private String name;
    private String status;
    private String birthday;
    private String location;
    private String about;
    private Set<Angler> friends;

    public SelfAngler(String name, String status, String birthday, String location, String about, Set<Angler> friends) {
        this.name = name;
        this.status = status;
        this.birthday = birthday;
        this.location = location;
        this.about = about;
        this.friends = friends;
    }

    static public SelfAngler build(Angler angler) {
        Set<Angler> friends = angler.getRelationships();
        friends.addAll(angler.getInverseRelationships());
        return new SelfAngler(
                angler.getName(),
                angler.getStatus(),
                angler.getLocation(),
                angler.getBirthday(),
                angler.getAbout(),
                friends
        );
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

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Set<Angler> getFriends() {
        return friends;
    }

    public void setFriends(Set<Angler> friends) {
        this.friends = friends;
    }
}
