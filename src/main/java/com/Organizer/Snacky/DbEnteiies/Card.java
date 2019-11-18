package com.Organizer.Snacky.DbEnteiies;

import com.Organizer.Snacky.Models.CardAddOrEditModel;
import com.Organizer.Snacky.Models.CardModel;
import com.Organizer.Snacky.Models.DescriptionModel;
import com.Organizer.Snacky.Models.UserModel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cards")
public class Card {
    public Card(String title, String description, Integer userId, Timestamp startDate, Timestamp endDate) {
        this.title = title;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        members = new HashSet<User>();
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
    @Column(name = "description", nullable = false)
    public String description;
    @Column(name = "user_id", nullable = false)
    public Integer userId;
    @Column(name = "start_date", nullable = true)
    public Timestamp startDate;
    @Column(name = "end_date", nullable = true)
    public Timestamp endDate;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_cards",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    public Set<User> members;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, updatable = false, insertable = false)
    public User user;

    public void updateFromModel(CardModel model) {
        title = model.title;
        startDate = model.startDate;
        endDate = model.endDate;
        description = model.description.content;

    }

    public CardModel toCardModel() {
        var cardModel =  new CardModel();
        cardModel.endDate = endDate;
        cardModel.title = title;
        cardModel.description.content = description;
        cardModel.creator.username = user.userName;
        for (var member: members
             ) {
            cardModel.members.add(member.toModel());
        }
        return cardModel;

    }
}



