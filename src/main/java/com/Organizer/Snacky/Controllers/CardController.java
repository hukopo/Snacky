package com.Organizer.Snacky.Controllers;

import com.Organizer.Snacky.DbEnteiies.User;
import com.Organizer.Snacky.DBRepos.UserRepository;
import com.Organizer.Snacky.DbEnteiies.Card;
import com.Organizer.Snacky.Models.CardModel;
import com.Organizer.Snacky.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
        card.members.add(user);
        card.user = user;
        var memberModels = cardAddOrEditModel.members;
        var memberNames = new ArrayList<String>();
        var members = new ArrayList<User>();
        if (memberModels != null)
            for (var userModel : memberModels
            ) {
                var member = userRepository.findByUserName(userModel.userName);
                members.add(member);
            }
        //var members = userRepository.findAllByUserName(memberNames);
        card.members.addAll(members);
        var added = service.addCard(card);
        return ok(added.toCardModel());
    }


    @GetMapping("/getAll")
    ResponseEntity getAll() {
        var auth = getAuthentication();
        if (auth == null)
            return unauthorized("");
        var user = userRepository.findByUserName(auth.getName());

        var cards = user.participantsCards;
        var cardsModels = new ArrayList<CardModel>();
        for (var card : cards
        ) {
            cardsModels.add(card.toCardModel());
        }
        return ok(cardsModels);
    }

    @PatchMapping("/{cardId}/edit")
    ResponseEntity add(@RequestBody CardModel cardAddOrEditModel, @PathVariable Integer cardId) {
        var auth = getAuthentication();
        if (auth == null)
            return unauthorized("");
        var userId = userRepository.findByUserName(auth.getName()).id;
        Card card = service.getCardById(cardId);
        if (card == null) {
            return notFound(String.format("card with specified id not found"));
        }
        if (card.userId != userId) {
            return forbidden("ne twoe");
        }
        card.updateFromModel(cardAddOrEditModel);
        var memberModels = cardAddOrEditModel.members;
        var members = new ArrayList<User>();
        if (memberModels != null)
            for (var userModel : memberModels
            ) {
                var member = userRepository.findByUserName(userModel.userName);
                members.add(member);
            }
        card.members.clear();
        card.members.addAll(members);
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
        if (cardToDelete.userId != userId) {
            return forbidden("ne twoe");
        }
        service.deleteById(cardId);
        return ok("");
    }


}
