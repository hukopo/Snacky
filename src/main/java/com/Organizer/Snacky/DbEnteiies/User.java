package com.Organizer.Snacky.DbEnteiies;

import com.Organizer.Snacky.Models.UserModel;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Transient
    private String passwordConfirm;

    public User(){}
 public User(String userName, String hash){
    this.userName = userName;
    this.hash = hash;
    participantsCards = new HashSet<>();
    userCards = new HashSet<>();
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

   @ManyToMany(mappedBy = "participants",targetEntity = Card.class)
   Set<Card> participantsCards;
   @OneToMany(mappedBy = "user")
   Set<Card> userCards;



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
        userModel.Username = userName;
        return userModel;
    }
}
