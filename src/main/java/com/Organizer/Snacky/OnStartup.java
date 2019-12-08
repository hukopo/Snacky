package com.Organizer.Snacky;

import com.Organizer.Snacky.DbEnteiies.User;
import com.Organizer.Snacky.Services.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OnStartup implements InitializingBean {

    @Autowired
    private UserService userService;

    @Override
    public void afterPropertiesSet() throws Exception {
        userService.addUser("Kartoshkin", "fullcontrol", User.Role.Admin);
        userService.addUser("Kapustin", "123", User.Role.User);
    }
}
