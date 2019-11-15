package com.Organizer.Snacky.Controllers;

import com.Organizer.Snacky.Models.CardAddOrEditModel;
import com.Organizer.Snacky.DBRepos.UserRepository;
import com.Organizer.Snacky.DbEnteiies.Card;
import com.Organizer.Snacky.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cards")
public class CardController extends BaseController {

    @Qualifier("cardServiceImpl")
    @Autowired
    private CardService service;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    ResponseEntity add(@RequestBody CardAddOrEditModel cardAddOrEditModel) {
        var userId = 1;
        Card card = cardAddOrEditModel.toCard(userId);
        var user = userRepository.findById(userId).get();
        card.participants.add(user);
        var participants = userRepository.findAllById(cardAddOrEditModel.participantsIds);
        card.participants.addAll(participants);
        Card added = service.addCard(card);
        return ok(added);
    }

    @GetMapping("/getAll")
    ResponseEntity getAll(Integer userId) {
        return ok(service.getCardsByUserId(userId));
    }

    @PatchMapping("/{cardId}/edit")
    ResponseEntity add(@RequestBody CardAddOrEditModel cardAddOrEditModel, @PathVariable Integer cardId) {
        Card card = service.getCardById(cardId);
        if (card == null) {
            return notFound(String.format("card with specified id not found"));
        }
        card.updateFromModel(cardAddOrEditModel);
        service.addCard(card);
        return ok(card);
    }
    @DeleteMapping("/{cardId}/delete")
    ResponseEntity add(@PathVariable Integer cardId) {
        var cardToDelete = service.getCardById(cardId);
        if (cardToDelete==null){
            return notFound("card with specified id not found");
        }
        service.deleteById(cardId);
        return ok("");
    }



}
