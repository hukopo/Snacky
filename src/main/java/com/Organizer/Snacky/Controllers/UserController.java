package com.Organizer.Snacky.Controllers;

import com.Organizer.Snacky.DBRepos.UserRepository;
import com.Organizer.Snacky.DbEnteiies.User;
import com.Organizer.Snacky.Models.CardModel;
import com.Organizer.Snacky.Models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("users")

public class UserController extends BaseController {
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/getAll")
    ResponseEntity getAll() {
        var usersEntities = userRepository.findAll();
        var userModels = new ArrayList<UserModel>();
        for (var userEntity : usersEntities
        ) {
            userModels.add(new UserModel(userEntity.userName));
        }
        return ok(userModels);
    }
}
