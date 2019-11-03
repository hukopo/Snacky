package com.Organizer.Snacky.Controllers;

import com.Organizer.Snacky.DbEnteiies.Card;
import com.Organizer.Snacky.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TestController {

    @Qualifier("cardServiceImplementation")
    @Autowired
    private CardService service;


    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String greeting() {
        return "hello";
    }

    @RequestMapping(value = "/addCard", method = RequestMethod.GET)
    public String addCard(String id, String text) {
        service.addCard(new Card("CARTOCHkA","AuthorizedUserName"));
        return "";
    }

}