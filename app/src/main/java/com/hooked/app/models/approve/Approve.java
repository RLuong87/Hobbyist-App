package com.hooked.app.models.approve;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.hooked.app.models.angler.Angler;
import com.hooked.app.models.content.Content;

import javax.persistence.*;

@Entity
public class Approve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "angler_id", referencedColumnName = "id")
    @JsonIncludeProperties("id")
    private Angler angler;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "content_id", referencedColumnName = "id")
    private Content content;

    public Approve() {
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

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
