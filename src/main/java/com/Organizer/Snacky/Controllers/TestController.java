package com.Organizer.Snacky.Controllers;

import DbEnteiies.Card;
import Services.CardServiceImplementation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TestController {


    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String greeting() {
        return "hui sosi gyboi traci";
    }

    @RequestMapping(value = "/addCard", method = RequestMethod.GET)
    public String addCard(String id, String text) {
        CardServiceImplementation service = new CardServiceImplementation();
        service.addCard(new Card("12","CARTOCHkA"));
        return "";
    }

}