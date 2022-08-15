package com.hooked.app.models.content;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.hooked.app.models.angler.Angler;
import com.hooked.app.models.angler.Comment;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "angler_id", referencedColumnName = "id")
    @JsonIncludeProperties({"avatar", "name"})
    private Angler angler;

    @CreationTimestamp
    private LocalDateTime localDateTime;

    private String picture;
    private String content;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "content",
            cascade = CascadeType.ALL)
    private List<Comment> comments;

    public Content() {
    }

    public Content(Angler angler, LocalDateTime localDateTime, String picture, String content, List<Comment> comments) {
        this.angler = angler;
        this.localDateTime = localDateTime;
        this.picture = picture;
        this.content = content;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Angler getAngler() {
        return angler;
    }

    public void setAngler(Angler angler) {
        this.angler = angler;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}