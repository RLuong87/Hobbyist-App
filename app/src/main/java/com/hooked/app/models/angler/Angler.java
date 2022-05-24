package com.hooked.app.models.angler;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hooked.app.models.avatar.Avatar;
import com.hooked.app.models.content.Content;
import com.hooked.app.models.auth.User;
import org.hibernate.annotations.WhereJoinTable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Angler {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String status;
    private String birthday;
    private String location;
    private String about;

    @OneToOne
    private Avatar avatar;

    @OneToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "angler")
    private List<Content> content;

    @ManyToMany()
    @JoinTable(
            name="relationship",
            joinColumns = @JoinColumn(name="originator_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="recipient_id", referencedColumnName = "id")
    )
    @WhereJoinTable(clause = "type = 'ACCEPTED'")
    @JsonIgnore
    private Set<Angler> relationships = new HashSet<>();

    @ManyToMany()
    @JoinTable(
            name="relationship",
            joinColumns = @JoinColumn(name="recipient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="originator_id", referencedColumnName = "id")
    )
    @JsonIgnore
    @WhereJoinTable(clause = "type = 'ACCEPTED'")
    private Set<Angler> inverseRelationships = new HashSet<>();

    @ManyToMany()
    @JoinTable(
            name="relationship",
            joinColumns = @JoinColumn(name="originator_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="recipient_id", referencedColumnName = "id")
    )
    @WhereJoinTable(clause = "type = 'PENDING'")
    @JsonIgnore
    private Set<Angler> pendingRelationships = new HashSet<>();

    @ManyToMany()
    @JoinTable(
            name="relationship",
            joinColumns = @JoinColumn(name="recipient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="originator_id", referencedColumnName = "id")
    )
    @JsonIgnore
    @WhereJoinTable(clause = "type = 'PENDING'")
    private Set<Angler> incomingRelationships = new HashSet<>();

    public Angler() {

    }

    public Angler(String name, String status, String birthday, String location, String about, Avatar avatar) {
        this.name = name;
        this.status = status;
        this.birthday = birthday;
        this.location = location;
        this.about = about;
        this.avatar = avatar;
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

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
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

    public Set<Angler> getRelationships() {
        return relationships;
    }

    public void setRelationships(Set<Angler> relationships) {
        this.relationships = relationships;
    }

    public Set<Angler> getInverseRelationships() {
        return inverseRelationships;
    }

    public void setInverseRelationships(Set<Angler> inverseRelationships) {
        this.inverseRelationships = inverseRelationships;
    }

    public Set<Angler> getPendingRelationships() {
        return pendingRelationships;
    }

    public void setPendingRelationships(Set<Angler> pendingRelationships) {
        this.pendingRelationships = pendingRelationships;
    }

    public Set<Angler> getIncomingRelationships() {
        return incomingRelationships;
    }

    public void setIncomingRelationships(Set<Angler> incomingRelationships) {
        this.incomingRelationships = incomingRelationships;
    }
}
