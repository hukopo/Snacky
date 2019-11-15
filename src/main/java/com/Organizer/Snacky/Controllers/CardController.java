package com.Organizer.Snacky.Controllers;

import com.Organizer.Snacky.DbEnteiies.User;
import com.Organizer.Snacky.Models.CardAddOrEditModel;
import com.Organizer.Snacky.DBRepos.UserRepository;
import com.Organizer.Snacky.DbEnteiies.Card;
import com.Organizer.Snacky.Models.CardModel;
import com.Organizer.Snacky.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("cards")
public class CardController extends BaseController {

    @Qualifier("cardServiceImpl")
    @Autowired
    private CardService service;
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/add")
    ResponseEntity add(@RequestBody CardModel cardAddOrEditModel) {
        var auth = getAuthentication();
        if (auth == null)
            return unauthorized("");
        var userName = auth.getName();
        var user = userRepository.findByUserName(userName);
        var card = cardAddOrEditModel.toCard(user.id);
        card.participants.add(user);
        var members = cardAddOrEditModel.Members;
        var memberNames = new ArrayList<String>();
        for (var member : members
        ) {
            memberNames.add(member.Username);
        }
        Iterable<User> participants = userRepository.findAllByUserName(memberNames);
        card.participants.addAll((Collection<? extends User>) participants);
        var added = service.addCard(card);
        return ok(added.toCardModel());
    }


    @GetMapping("/getAll")
    ResponseEntity getAll() {
        var auth = getAuthentication();
        if (auth == null)
            return unauthorized("");
        var userId = userRepository.findByUserName(auth.getName()).id;
        var cards = service.getCardsByUserId(userId);
        var cardsModels = new ArrayList<CardModel>();
        for (var card : cards
        ) {
            cardsModels.add(card.toCardModel());
        }
        return ok(cardsModels);
    }

    @PatchMapping("/{cardId}/edit")
    ResponseEntity add(@RequestBody CardAddOrEditModel cardAddOrEditModel, @PathVariable Integer cardId) {
        var auth = getAuthentication();
        if (auth == null)
            return unauthorized("");
        var userId = userRepository.findByUserName(auth.getName()).id;
        Card card = service.getCardById(cardId);
        if (card == null) {
            return notFound(String.format("card with specified id not found"));
        }
        if (card.userId!=userId){
            return badRequest("ne twoe");
        }
        card.updateFromModel(cardAddOrEditModel);
        service.addCard(card);
        return ok(card.toCardModel());
    }

    @DeleteMapping("/{cardId}/delete")
    ResponseEntity add(@PathVariable Integer cardId) {
        var auth = getAuthentication();
        if (auth == null)
            return unauthorized("");
        var userId = userRepository.findByUserName(auth.getName()).id;
        var cardToDelete = service.getCardById(cardId);
        if (cardToDelete == null) {
            return notFound("card with specified id not found");
        }
        if (cardToDelete.userId!=userId){
            return badRequest("ne twoe");
        }
        service.deleteById(cardId);
        return ok("");
    }


}
