package com.musicapplication.musicapplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {
    // Display the registration page for users
    @GetMapping("/userregisteration")
    public String addUser() {
        return "registration";
    }

    // Display the login page for users
    @GetMapping("/userlogin")
    public String loginUser() {
        return "userlogin";
    }

    // Display the forgot password page
    @GetMapping("/forgotpassword")
    public String forgotPassword() {
        return "forgotpassword";
    }

    // Display the page for adding songs
    @GetMapping("/addsongs")
    public String addSongs() {
        return "addsongs";
    }

    // Display the admin home page
    @GetMapping("/adminhome")
    public String adminHome() {
        return "adminhome";
    }

    // Display the page for adding a playlist
    @GetMapping("/addplaylist")
    public String addPlaylist() {
        return "createPlaylist";
    }

    // Display the payment page
    @GetMapping("/Pay")
    public String Payment() {
        return "pay";
    }

}


