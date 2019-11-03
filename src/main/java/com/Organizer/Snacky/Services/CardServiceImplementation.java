package com.Organizer.Snacky.Services;

import com.Organizer.Snacky.DBRepos.CardRepository;
import com.Organizer.Snacky.DbEnteiies.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImplementation implements CardService {

    @Autowired
    private CardRepository cardsRepository;

    @Override
    public Card addCard(Card card) {
       Card savedCard =   cardsRepository.saveAndFlush(card);


        return savedCard;
    }


}
