package com.Organizer.Snacky.DbEnteiies;

import com.Organizer.Snacky.Models.CardModel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
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
        tags = new HashSet<>();
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
    @Column(name = "place_id", nullable = true)
    public Integer placeId;
    @Column(name = "start_date", nullable = true)
    public Timestamp startDate;
    @Column(name = "end_date", nullable = true)
    public Timestamp endDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_cards",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    public Set<User> members;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "card_tags",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    public Set<Tag> tags;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, updatable = false, insertable = false)
    public User user;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST},fetch = FetchType.EAGER, targetEntity = Place.class)
    @JoinColumn(name = "place_id", nullable = true, updatable = false, insertable = false)
    public Place place;

    public void updateFromModel(CardModel model) {
        title = model.title;
        startDate = model.startDate;
        endDate = model.endDate;
        description = model.description.content;

    }

    public CardModel toCardModel() {
        var cardModel =  new CardModel();
        cardModel.id = id;
        cardModel.endDate = endDate;
        cardModel.title = title;
        cardModel.description.content = description;
        cardModel.creator.userName = user.userName;
        cardModel.place = place.toModel();
        for (var member: members
             ) {
            cardModel.members.add(member.toModel());
        }
        for (var tag: tags
        ) {
            cardModel.tags.add(tag.toModel());
        }

        return cardModel;

    }
}



