package com.hooked.app.payloads.response;

import com.hooked.app.models.angler.Angler;

import java.util.Set;

public class SelfAngler {

    private Long id;
    private String name;
    private String status;
    private String location;
    private Set<Angler> friends;
    private Set<Angler> pendingFriends;
    private Set<Angler> incomingFriends;

    public SelfAngler(Long id, String name, String status, String location, Set<Angler> friends, Set<Angler> pendingFriends, Set<Angler> incomingFriends) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.location = location;
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
                angler.getLocation(),
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
