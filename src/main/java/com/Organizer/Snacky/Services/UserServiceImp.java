package com.Organizer.Snacky.Services;

import com.Organizer.Snacky.DBRepos.UserRepository;
import com.Organizer.Snacky.DbEnteiies.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public void addUser(String userName, String hash) {
        userRepository.saveAndFlush(new User(userName,hash));
    }
}
