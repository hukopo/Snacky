package com.Organizer.Snacky.Controllers;

import com.Organizer.Snacky.DBRepos.PlaceRepository;
import com.Organizer.Snacky.DBRepos.TagRepository;
import com.Organizer.Snacky.DbEnteiies.Tag;
import com.Organizer.Snacky.DBRepos.UserRepository;
import com.Organizer.Snacky.DbEnteiies.Card;
import com.Organizer.Snacky.Models.CardModel;
import com.Organizer.Snacky.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;

@RestController
@RequestMapping("cards")
public class CardController extends BaseController {

    @Qualifier("cardServiceImpl")
    @Autowired
    private CardService service;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private PlaceRepository placeRepository;


    @PostMapping("/add")
    ResponseEntity add(@RequestBody CardModel cardModel) {
        var auth = getAuthentication();
        if (auth == null)
            return unauthorized("");
        var userName = auth.getName();
        var user = userRepository.findByUserName(userName).get();
        var card = cardModel.toCard(user.id);
        card.members.add(user);
        card.user = user;
        FillMembers(cardModel, card);
        FillTags(cardModel, card);
        FillPlace(cardModel, card);
        var added = service.addCard(card);
        return ok(added.toCardModel());
    }

    private void FillPlace(@RequestBody CardModel cardModel, Card card) {
        var place = cardModel.place.toEntity();
        var placeId = placeRepository.saveAndFlush(place).id;
        card.place = place;
        card.placeId = placeId;
    }


    @GetMapping("/getAll")
    ResponseEntity getAll(int take, int skip) {
        var auth = getAuthentication();
        if (auth == null)
            return unauthorized("");
        var user = userRepository.findByUserName(auth.getName()).get();
        var cards = user.participantsCards;
        var cardsModels = new ArrayList<CardModel>();
        var i = 0;
        for (var card : cards
        ) {
            if (i > skip + take)
                break;
            i++;
            if (i > skip)
                cardsModels.add(card.toCardModel());
        }
        cardsModels.sort(Comparator.comparingInt(a -> a.id));
        return ok(cardsModels);
    }

    @PatchMapping("/{cardId}/edit")
    ResponseEntity add(@RequestBody CardModel cardModel, @PathVariable Integer cardId) {
        var auth = getAuthentication();
        if (auth == null)
            return unauthorized("");
        var userId = userRepository.findByUserName(auth.getName()).get().id;
        Card card = service.getCardById(cardId);
        if (card == null) {
            return notFound(String.format("card with specified id not found"));
        }
        if (card.userId != userId) {
            return forbidden("ne twoe");
        }
        card.updateFromModel(cardModel);
        card.members.clear();
        FillMembers(cardModel, card);
        card.tags.clear();
        FillTags(cardModel, card);
        service.addCard(card);
        return ok(card.toCardModel());
    }

    @DeleteMapping("/{cardId}/delete")
    ResponseEntity add(@PathVariable Integer cardId) {
        var auth = getAuthentication();
        if (auth == null)
            return unauthorized("");
        var userId = userRepository.findByUserName(auth.getName()).get().id;
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

    private void FillTags(@RequestBody CardModel cardModel, Card card) {
        var tags = cardModel.tags;
        if (tags != null)
            for (var tag : tags
            ) {
                var existingTag = tagRepository.findByName(tag.name);
                if (existingTag.isPresent())
                    card.tags.add(existingTag.get());
                else {
                    var newTag = new Tag(tag.name);
                    tagRepository.saveAndFlush(newTag);
                    card.tags.add(newTag);
                }
            }
    }

    private void FillMembers(@RequestBody CardModel cardModel, Card card) {
        var memberModels = cardModel.members;
        if (memberModels != null)
            for (var userModel : memberModels
            ) {
                var member = userRepository.findByUserName(userModel.userName);
                member.ifPresent(user -> card.members.add(user));
            }
    }

}
