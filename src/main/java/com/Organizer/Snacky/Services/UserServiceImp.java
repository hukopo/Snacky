package com.Organizer.Snacky.Services;

import com.Organizer.Snacky.DBRepos.UserRepository;
import com.Organizer.Snacky.DbEnteiies.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void addUser(String userName, String password) {
        var user = new User(userName, bCryptPasswordEncoder.encode(password));
        userRepository.saveAndFlush(user);
    }

    @Override
    public Iterable<User> getUsersByLogin(String userName) {
        return userRepository.findAllByUserName(userName);
    }
}
