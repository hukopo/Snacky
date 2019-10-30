package com.Organizer.Snacky.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TestController {

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String greeting() {
        return "greeting";
    }

}