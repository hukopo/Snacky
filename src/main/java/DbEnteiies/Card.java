package DbEnteiies;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Cards")
public class Card {
    public Card(String id, String text){
        this.id = id;
        this.text = text;
    }
    private String text;
    @Id
    @GeneratedValue
    private String id;

}
