package com.Organizer.Snacky.Controllers;

import com.Organizer.Snacky.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    void register(String username, String password){

        userService.addUser("userName","pass");
    }
}
