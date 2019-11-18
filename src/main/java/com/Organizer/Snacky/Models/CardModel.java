package com.Organizer.Snacky.Models;

import java.sql.Timestamp;
import java.util.ArrayList;

public  class CardModel {
    public CardModel(){
        description =new DescriptionModel();
        creator = new UserModel();
        place = new PlaceModel();
        members = new ArrayList<>();
    };
    public String title;
    public UserModel creator;
    public DescriptionModel description;
    public ArrayList<UserModel> members;
    public PlaceModel place;
    public Timestamp startDate;
    public Timestamp endDate;

    public com.Organizer.Snacky.DbEnteiies.Card toCard(Integer userId) {
        return new com.Organizer.Snacky.DbEnteiies.Card(title, description.content, userId, startDate, endDate);
    }
}
