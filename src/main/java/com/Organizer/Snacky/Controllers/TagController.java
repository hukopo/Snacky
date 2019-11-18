package com.Organizer.Snacky.Controllers;

import com.Organizer.Snacky.DBRepos.TagRepository;
import com.Organizer.Snacky.DBRepos.UserRepository;
import com.Organizer.Snacky.DbEnteiies.Tag;
import com.Organizer.Snacky.Models.TagModel;
import com.Organizer.Snacky.Models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("tags")
public class TagController extends BaseController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TagRepository tagRepository;

    @PostMapping("/add")
    ResponseEntity add(@RequestBody TagModel tagModel) {
        var user = getAuthentication();
        if (user == null)
            return unauthorized("");
        var existingTag = tagRepository.findByName(tagModel.name);
        if (existingTag.isPresent()){
            return badRequest("tag already exists");
        }
        var tag = new Tag(tagModel.name);
        var added = tagRepository.saveAndFlush(tag);
        return ok(added);
    }

    @GetMapping("/getAll")
    ResponseEntity getAll(@RequestBody TagModel tagModel) {
        var tagEntities = tagRepository.findAll();
        var tagModels = new ArrayList<TagModel>();
        for (var entity: tagEntities
             ) {
            tagModels.add(new TagModel(entity.name));
        }
        return ok(tagModels);
    }




}
