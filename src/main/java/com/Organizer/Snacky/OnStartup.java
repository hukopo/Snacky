package com.Organizer.Snacky;

import com.Organizer.Snacky.DBRepos.PlaceRepository;
import com.Organizer.Snacky.DBRepos.UserRepository;
import com.Organizer.Snacky.DbEnteiies.Card;
import com.Organizer.Snacky.DbEnteiies.Place;
import com.Organizer.Snacky.DbEnteiies.User;
import com.Organizer.Snacky.Services.CardService;
import com.Organizer.Snacky.Services.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class OnStartup implements InitializingBean {

    @Autowired
    private UserService userService;
    @Autowired
    private CardService cardService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PlaceRepository placeRepository;

    @Override
    public void afterPropertiesSet() {
        userService.addUser("Kartoshkin", "fullcontrol", User.Role.Admin);
        userService.addUser("Kapustin", "123", User.Role.User);

        var kartoshkin = userRepository.findByUserName("Kartoshkin").get();
        var kapustin = userRepository.findByUserName("Kapustin").get();

        var card1 = new Card("Карточка1","Описание1",kartoshkin.id,null,null);
        card1.place = new Place();

        card1.place.name = "Место1";
        card1.place.longitude = 228f;
        card1.place.latitude = 1488f;
        var place = placeRepository.saveAndFlush(card1.place);
        card1.place = place;
        card1.placeId = place.id;
        card1.userId = kartoshkin.id;
        card1.members = new HashSet<>();

        card1.members.add(userRepository.findByUserName(kartoshkin.userName).get());
        card1.members.add(userRepository.findByUserName(kapustin.userName).get());

        var card2 = new Card();
        card2.title = "Карточка2";
        card2.place = new Place();
        card2.place.name = "Место2";
        card2.place.longitude = 1337f;
        card2.place.latitude = 1337f;
        place = placeRepository.saveAndFlush(card2.place);
        card2.place = place;
        card2.placeId = place.id;
        card2.members = new HashSet<>();
        card2.description = "Описание2";
        //card2.user = kapustin;
        card2.userId = kapustin.id;
        var card = cardService.addCard(card1);
        cardService.addCard(card2);
    }
}
