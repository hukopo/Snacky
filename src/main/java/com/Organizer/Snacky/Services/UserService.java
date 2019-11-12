package com.Organizer.Snacky.Services;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public void addUser(String userName, String hash);
}
