package com.Organizer.Snacky.Models;

import java.sql.Timestamp;
import java.util.ArrayList;

public  class CardModel {
    public CardModel(){
        Description=new DescriptionModel();
        Place = new PlaceModel();
    };
    public String Name;
    public UserModel Author;
    public DescriptionModel Description;
    public ArrayList<UserModel> Members;
    public PlaceModel Place;
    public Timestamp StartDate;
    public Timestamp EndDate;

    public com.Organizer.Snacky.DbEnteiies.Card toCard(Integer userId) {
        return new com.Organizer.Snacky.DbEnteiies.Card(Name, Description.Content, userId, StartDate, EndDate);
    }
}
