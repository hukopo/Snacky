package com.Organizer.Snacky.Services;

import com.Organizer.Snacky.DbEnteiies.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public void addUser(String userName, String hash, User.Role role);
    //Iterable<User> getUsersByLogin(String userName);
}
