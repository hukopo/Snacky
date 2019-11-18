package com.Organizer.Snacky.DbEnteiies;

import com.Organizer.Snacky.Models.PlaceModel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
@Entity
@Table(name = "places")
public class Place {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "place_id", nullable = false)
    public Integer id;
    @Column(name = "name", nullable = true)
    public String  name;
    @Column(name = "longitude", nullable = true)
    public Float longitude;
    @Column(name = "latitude", nullable = true)
    public Float latitude;
    @OneToMany(mappedBy = "place")
    public Set<Card> placeCards;

    public Place() {
    }

    public PlaceModel toModel() {
        var model = new PlaceModel();
        model.latitude = latitude;
        model.longitude = longitude;
        model.name = name;
        return model;
    }
}
