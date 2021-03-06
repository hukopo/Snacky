package com.Organizer.Snacky.Controllers;

import com.Organizer.Snacky.DbEnteiies.User;
import com.Organizer.Snacky.Services.SecurityService;
import com.Organizer.Snacky.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registrationPage.jsp";
    }


    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        //userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registrationPage.jsp";
        }

        userService.addUser(userForm.userName, userForm.hash, User.Role.User);

        securityService.autoLogin(userForm.userName, userForm.hash);


        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "loginPage.jsp";
    }

    @GetMapping("/")
    public String home(Model model) {
        return "index.html";
    }

    @GetMapping("/welcome")
    public String welcome(Model model) {
        return "welcome.jsp";
    }
}
