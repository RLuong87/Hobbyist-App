package com.hooked.app.models.avatar;

import com.hooked.app.models.angler.Angler;

import javax.persistence.*;

@Entity
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private Long size;
    private byte[] content;

    @OneToOne
    private Angler angler;

    public Avatar(String url, Angler angler) {
        this.url = url;
        this.angler = angler;
    }

    public Avatar() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Angler getAngler() {
        return angler;
    }

    public void setAngler(Angler angler) {
        this.angler = angler;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
