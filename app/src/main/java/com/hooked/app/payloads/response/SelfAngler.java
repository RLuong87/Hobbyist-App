package com.hooked.app.payloads.response;

import com.hooked.app.models.angler.Angler;

import java.util.Set;

public class SelfAngler {

    private Long id;
    private String name;
    private String email;
    private String status;
    private String birthday;
    private String location;
    private String about;
    private Set<Angler> friends;
    private Set<Angler> pendingFriends;
    private Set<Angler> incomingFriends;

    public SelfAngler(Long id, String name, String email, String status, String birthday, String location, String about, Set<Angler> friends, Set<Angler> pendingFriends, Set<Angler> incomingFriends) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
        this.birthday = birthday;
        this.location = location;
        this.about = about;
        this.friends = friends;
        this.pendingFriends = pendingFriends;
        this.incomingFriends = incomingFriends;
    }

    static public SelfAngler build(Angler angler) {
        Set<Angler> friends = angler.getRelationships();
        friends.addAll(angler.getInverseRelationships());
        return new SelfAngler(
                angler.getId(),
                angler.getName(),
                angler.getStatus(),
                angler.getBirthday(),
                angler.getLocation(),
                angler.getAbout(),
                friends,
                angler.getPendingRelationships(),
                angler.getIncomingRelationships()
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Set<Angler> getFriends() {
        return friends;
    }

    public void setFriends(Set<Angler> friends) {
        this.friends = friends;
    }

    public Set<Angler> getPendingFriends() {
        return pendingFriends;
    }

    public void setPendingFriends(Set<Angler> pendingFriends) {
        this.pendingFriends = pendingFriends;
    }

    public Set<Angler> getIncomingFriends() {
        return incomingFriends;
    }

    public void setIncomingFriends(Set<Angler> incomingFriends) {
        this.incomingFriends = incomingFriends;
    }
}
