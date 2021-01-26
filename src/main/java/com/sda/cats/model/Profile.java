package com.sda.cats.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "profiles")
public class Profile {

    @Id
    @Column(length = 50) //warto ustawiać, bo inaczej SQL może się wysypać
    private String username;

    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Role> roles;

    public Profile() {
    }

    public Profile(String username, String password, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
