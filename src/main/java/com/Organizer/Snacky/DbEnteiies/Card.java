package com.Organizer.Snacky.DbEnteiies;

import Models.CardAddOrEditModel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cards")
public class Card {
    public Card(String title, Integer userId, Timestamp startDate, Timestamp endDate) {
        this.title = title;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        participants = new HashSet<>();
    }

    public Card() {
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "card_id", nullable = false)
    public Integer id;
    @Column(name = "title", nullable = false)
    public String title;
    @Column(name = "user_id", nullable = false)
    public Integer userId;
    @Column(name = "start_date", nullable = true)
    public Timestamp startDate;
    @Column(name = "end_date", nullable = true)
    public Timestamp endDate;

    @ManyToMany(cascade={CascadeType.MERGE,CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name="user_cards",
            joinColumns=@JoinColumn(name="card_id"),
            inverseJoinColumns=@JoinColumn(name="user_id"))
    public Set<User> participants;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false, insertable = false)
    User user;

    public void updateFromModel(CardAddOrEditModel model) {
        title = model.title;
        startDate = model.startDate;
        endDate = model.endDate;
    }






}
