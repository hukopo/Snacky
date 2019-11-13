package Models;

import com.Organizer.Snacky.DbEnteiies.Card;

import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.util.List;

public class CardAddOrEditModel {
    public String title;
    public Timestamp startDate;
    public Timestamp endDate;
    public List<Integer> participantsIds;
    public Card toCard(Integer userId){
        return new Card(title,userId,startDate,endDate);
    }
}
