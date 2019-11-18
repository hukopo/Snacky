package com.Organizer.Snacky.DbEnteiies;

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
    @Column(name = "tagColor", nullable = false)
    public Color color;
    @ManyToMany(mappedBy = "tags", targetEntity = Card.class)
    public Set<Card> cardsWithTag;

}
