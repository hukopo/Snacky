package com.Organizer.Snacky.Services;

import com.Organizer.Snacky.DbEnteiies.Card;
import org.springframework.stereotype.Service;

@Service
public interface CardService {
    public Card addCard(Card card);
}
