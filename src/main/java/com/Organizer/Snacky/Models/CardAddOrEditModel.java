package com.Organizer.Snacky.Models;

import com.Organizer.Snacky.DbEnteiies.Card;

import java.sql.Timestamp;
import java.util.List;


public class CardAddOrEditModel {
    public String title;
    public String description;
    public Timestamp startDate;
    public Timestamp endDate;
    public List<Integer> participantsIds;

    public Card toCard(Integer userId) {
        return new Card(title, description, userId, startDate, endDate);
    }
}
