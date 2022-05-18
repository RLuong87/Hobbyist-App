package com.hooked.app.models.relationship;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.hooked.app.models.angler.Angler;

import javax.persistence.*;

@Entity
public class Relationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "originator_id", referencedColumnName = "id")
    @JsonIncludeProperties({"id", "name", "type"})
    private Angler originator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id", referencedColumnName = "id")
    @JsonIncludeProperties({"id", "name", "type"})
    private Angler recipient;

    @Enumerated(EnumType.STRING)
    private ERelationship type;

    public Relationship() {
    }

    public Relationship(Angler originator, Angler recipient, ERelationship type) {
        this.originator = originator;
        this.recipient = recipient;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Angler getOriginator() {
        return originator;
    }

    public void setOriginator(Angler originator) {
        this.originator = originator;
    }

    public Angler getRecipient() {
        return recipient;
    }

    public void setRecipient(Angler recipient) {
        this.recipient = recipient;
    }

    public ERelationship getType() {
        return type;
    }

    public void setType(ERelationship type) {
        this.type = type;
    }
}
