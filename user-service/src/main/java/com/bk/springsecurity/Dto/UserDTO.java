package com.bk.springsecurity.Dto;

import org.springframework.data.mongodb.core.index.Indexed;

public class UserDTO {

    private String Id;
    private String Username;
    @Indexed(unique = true)
    private String email;
    private String password;

    public UserDTO() {
    }

    public UserDTO(String Id, String Username, String email, String password) {
        this.Id = Id;
        this.Username = Username;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}