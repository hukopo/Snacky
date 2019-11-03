package com.Organizer.Snacky.DbEnteiies;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "cards",schema = "cards")
public class Card {
    public Card( String text, String owner){
        this.text = text;
        this.owner = owner;
    }
    public Card(){}
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment",strategy = "increment")
    @Column(name = "id",nullable = false)
    public Integer id;
    @Column(name = "text",nullable = false)
    public String text;
    @Column(name = "description",nullable = true)
    public String description;
    @Column(name = "owner",nullable = false)
    public String owner;



}
