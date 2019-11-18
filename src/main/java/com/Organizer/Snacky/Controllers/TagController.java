package com.Organizer.Snacky.Controllers;

import com.Organizer.Snacky.DBRepos.TagRepository;
import com.Organizer.Snacky.DBRepos.UserRepository;
import com.Organizer.Snacky.Models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/*@RestController
@RequestMapping("tags")
public class TagController extends BaseController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TagRepository tagRepository;

    @PostMapping("/add")
    ResponseEntity add(@RequestBody ) {
        var user = getAuthentication();
        if (user == null)
            return unauthorized("");

    }



}
*/