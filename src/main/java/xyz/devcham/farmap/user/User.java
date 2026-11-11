package xyz.devcham.farmap.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {

    private String id;
    private String email;
    private String password;
    private String username;

    public User(String id, String email, String password, String username) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
