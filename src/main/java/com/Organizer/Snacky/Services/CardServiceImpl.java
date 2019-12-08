package com.Organizer.Snacky.Services;

import com.Organizer.Snacky.DBRepos.CardRepository;
import com.Organizer.Snacky.DbEnteiies.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardsRepository;

    @Override
    public Card addCard(Card card) {
        Card savedCard = cardsRepository.saveAndFlush(card);
        return savedCard;
    }

    @Override
    public Card getCardById(Integer id) {
        Optional<Card> card = cardsRepository.findById(id);
        if (card.isEmpty()) {
            return null;
        }
        return card.get();

    }

    @Override
    public Iterable<Card> getCardsByUserId(Integer userId) {
        return cardsRepository.findAllByUserId(userId);
    }
    @Override
    public Iterable<Card> getAllCards() {
        return cardsRepository.findAll();
    }

    @Override
    public void deleteById(Integer cardId) {
        cardsRepository.deleteById(cardId);
    }


}
