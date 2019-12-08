package com.Organizer.Snacky.DbEnteiies;

import com.Organizer.Snacky.Models.UserModel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Transient
    private String passwordConfirm;

    public User() {
    }

    public User(String userName, String hash, Role role) {
        this.userName = userName;
        this.hash = hash;
        participantsCards = new HashSet<>();
        userCards = new HashSet<>();
        this.role = role;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "user_id", nullable = false)
    public Integer id;
    @Column(name = "user_name", nullable = false)
    public String userName;
    @Column(name = "hash", nullable = false)
    public String hash;
    @Column(name = "role", nullable = false)
    public Role role;


    @ManyToMany(mappedBy = "members", targetEntity = Card.class)
    public Set<Card> participantsCards;
    @OneToMany(mappedBy = "user")
    public Set<Card> userCards;


    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return hash;
    }

    public void setPassword(String password) {
        this.hash = password;
    }


    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public UserModel toModel() {
        var userModel = new UserModel();
        userModel.userName = userName;
        return userModel;
    }

    public enum Role {
        User,
        Admin
    }
}
