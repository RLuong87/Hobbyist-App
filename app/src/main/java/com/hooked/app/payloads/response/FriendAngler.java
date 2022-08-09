package com.hooked.app.payloads.response;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.hooked.app.models.angler.Angler;
import com.hooked.app.models.avatar.Avatar;
import com.hooked.app.models.content.Content;

import java.util.List;
import java.util.Set;

public class FriendAngler extends PublicAngler {
    @JsonIncludeProperties({"id", "name"})
    private Set<Angler> friends;

    public FriendAngler(Avatar avatar, String name, String status, String birthday, String location, String about, List<Content> content) {
        super(avatar, name, status, birthday, location, about, content);
    }

    static public FriendAngler build(Angler angler) {
        return new FriendAngler(
                angler.getAvatar(),
                angler.getName(),
                angler.getStatus(),
                angler.getBirthday(),
                angler.getLocation(),
                angler.getAbout(),
                angler.getContent()
        );
    }

    public Set<Angler> getFriends() {
        return friends;
    }

    public void setFriends(Set<Angler> friends) {
        this.friends = friends;
    }
}
