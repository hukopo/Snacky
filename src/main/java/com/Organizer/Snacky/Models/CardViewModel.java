package com.Organizer.Snacky.Models;

import java.sql.Timestamp;
import java.util.List;

public class CardViewModel {
    public String title;
    public String description;
    public Timestamp startDate;
    public Timestamp endDate;
    public List<UserModel> participants;
}
