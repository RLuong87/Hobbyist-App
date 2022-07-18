package com.hooked.app.payloads.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hooked.app.models.angler.Angler;
import com.hooked.app.models.content.Content;

import java.util.List;

public class SelfContent {

    @JsonIgnore
    private Long id;
    private String name;
    private List<Content> content;

    public SelfContent(Long id, String name, List<Content> content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    static public SelfContent build(Angler angler) {
        return new SelfContent(
                angler.getId(),
                angler.getName(),
                angler.getContent()
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

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }
}
