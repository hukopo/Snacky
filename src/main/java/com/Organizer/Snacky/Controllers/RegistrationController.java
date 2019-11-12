package com.Organizer.Snacky.Controllers;

import com.Organizer.Snacky.DbEnteiies.User;
import com.Organizer.Snacky.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class RegistrationController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    void register(String username, String password) {
        userService.addUser("userName", "pass");
    }

    @PostMapping("/login")
    String login(String username, String password) {
        var usersList = new ArrayList<User>();
        userService.getUsersByLogin("userName").forEach(usersList::add);
        if (usersList.get(0).hash == password) {
            return "true";
        }
        return "false";
    }
}
