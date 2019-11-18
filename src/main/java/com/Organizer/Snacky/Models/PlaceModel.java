package com.Organizer.Snacky.Models;

import com.Organizer.Snacky.DbEnteiies.Place;

public class PlaceModel {
    public String name;
    public Float longitude;
    public Float latitude;

    public Place toEntity() {
        var place = new Place();
        place.latitude = latitude;
        place.longitude = longitude;
        place.name = name;
        return place;
    }
}
