package com.touchsoft.timetracker.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users",catalog = "timetracker")
public class User extends AbstractUser implements Serializable {
    @Column(name = "u_login")
    private String login;
    @JsonIgnore
    @Column(name = "u_password")
    private String password;


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}'+super.toString();
    }
}
