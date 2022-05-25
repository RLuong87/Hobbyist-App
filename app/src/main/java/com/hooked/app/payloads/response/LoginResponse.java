package com.hooked.app.payloads.response;

import java.util.List;

public class LoginResponse {

    private String token;
    private String name;
    private List<String> roles;
    private SelfAngler selfAngler;

    public LoginResponse(String token, String name, List<String> roles, SelfAngler selfAngler) {
        this.token = token;
        this.name = name;
        this.roles = roles;
        this.selfAngler = selfAngler;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public SelfAngler getSelfAngler() {
        return selfAngler;
    }

    public void setSelfAngler(SelfAngler selfAngler) {
        this.selfAngler = selfAngler;
    }
}
