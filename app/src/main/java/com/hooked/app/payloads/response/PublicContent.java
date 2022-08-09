package com.hooked.app.payloads.response;

import com.hooked.app.models.angler.Angler;
import com.hooked.app.models.content.Content;

import java.util.List;

public class PublicContent {

    private String name;
    private List<Content> content;

    public PublicContent(String name, List<Content> content) {
        this.name = name;
        this.content = content;
    }

    static public PublicContent build(Angler angler) {
        return new PublicContent(
                angler.getName(),
                angler.getContent()
        );
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
