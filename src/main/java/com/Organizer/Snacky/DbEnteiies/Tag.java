package com.Organizer.Snacky.DbEnteiies;

import com.Organizer.Snacky.Models.TagModel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.awt.*;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "tag_id", nullable = false)
    public Integer id;
    @Column(name = "tag_name", nullable = false)
    public String name;
    @ManyToMany(mappedBy = "tags", targetEntity = Card.class)
    public Set<Card> cardsWithTag;

    public Tag(String name) {
        this.name = name;
    }
    public Tag(){}

    public TagModel toModel() {
        return new TagModel(name);
    }
}
