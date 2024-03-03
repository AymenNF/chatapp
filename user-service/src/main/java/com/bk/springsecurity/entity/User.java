package com.bk.springsecurity.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")
public class User {
    @Id
    private String Id;
    @Indexed
    private String Username;
    private String email;
    private String password;
    private boolean active;

    public User(String Id, String Username, String email, String password) {
        this.Id = Id;
        this.Username = Username;
        this.email = email;
        this.password = password;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getId() {
        return Id;
    }

    public String getUsername() {
        return Username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return active;
    }
}
