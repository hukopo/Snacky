package com.Organizer.Snacky.Services;

import com.Organizer.Snacky.DbEnteiies.Card;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CardService {
    public Card addCard(Card card);
    public Card getCardById(Integer id);
    public Iterable<Card> getCardsByUserId(Integer userId);
    public void deleteById(Integer cardId);
}
