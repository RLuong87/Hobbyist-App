package com.hooked.app.payloads.response;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.hooked.app.models.angler.Angler;
import com.hooked.app.models.avatar.Avatar;

import java.util.Set;

public class FriendAngler extends PublicAngler {
    @JsonIncludeProperties({"id", "name"})
    private Set<Angler> friends;

    public FriendAngler(Long id, String name, Avatar avatar, String location, Set<Angler> friends) {
        super(id, name, avatar, location);
        this.friends = friends;
    }

    static public FriendAngler build(Angler angler) {
        Set<Angler> friends = angler.getRelationships();
        friends.addAll(angler.getInverseRelationships());
        return new FriendAngler(
                angler.getId(),
                angler.getName(),
                angler.getAvatar(),
                angler.getLocation(),
                friends
        );
    }

    public Set<Angler> getFriends() {
        return friends;
    }

    public void setFriends(Set<Angler> friends) {
        this.friends = friends;
    }
}
