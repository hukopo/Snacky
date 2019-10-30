package Services;

import DBRepos.CardsRepository;
import DbEnteiies.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

@Service
@Configurable
public class CardServiceImplementation implements CardService {

    @Autowired
    private CardsRepository cardsRepository;

    @Override
    public Card addCard(Card card) {
        Card savedCard = cardsRepository.saveAndFlush(card);

        return savedCard;
    }


}
