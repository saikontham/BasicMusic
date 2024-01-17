package com.musicapplication.musicapplication.controllers;

import com.musicapplication.musicapplication.entities.Songs;
import com.musicapplication.musicapplication.entities.User;
import com.musicapplication.musicapplication.services.SongServiceImplementation;
import com.musicapplication.musicapplication.services.UserService;
import com.musicapplication.musicapplication.services.UserServiceImplementation;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServiceImplementation userServiceImplementation;

    @Autowired
    private SongServiceImplementation songServiceImplementation;

    /**
     * Handles the user registration process.
     *
     * @param user The User object containing registration details.
     * @return A String representing the logical view name to redirect after registration.
     */
    @PostMapping("/register")
    public String handleRegistration(@ModelAttribute User user) {
        // Check if the provided email already exists in the system
        boolean isEmailExists = userServiceImplementation.isEmailExists(user.getEmail());
        // If email does not exist, add the user to the system
        if (!isEmailExists) {
            // If email already exists, log a message indicating the user exists

            userServiceImplementation.addUsers(user);
            logger.info("user added successfully");
        }
        logger.info("user already exists with email " + user.getEmail());
        // Redirect to the user login page regardless of registration outcome

        return "userlogin";
    }

    /**
     * Validates user login credentials and determines the appropriate redirection based on the user's role and subscription status.
     *
     * @param email    The user's email obtained from the login form.
     * @param password The user's password obtained from the login form.
     * @param session  HttpSession to manage user session attributes.
     * @param model    Model to add attributes for rendering the view.
     * @return A String representing the logical view name to redirect after login validation.
     */
    @PostMapping("/validate")
    public String valiadteLogin(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        // Validate user login credentials using the userServiceImplementation

        if (userServiceImplementation.validateLogin(email, password) == true) {
            // If login is successful, obtain the user's role

            String role = userServiceImplementation.validateRole(email);
            // Set the user's email as a session attribute

            session.setAttribute("email", email);

            if (role.equals("admin")) {
                // If the user is an admin, redirect to the adminhome page

                return "adminhome";
            } else {
                // If the user is not an admin, retrieve user details

                User user = userServiceImplementation.getUser(email);
                boolean userStatus = user.isPremium();
                List<Songs> songsList = songServiceImplementation.displaySongs();
                // Add the songs list to the model, making it accessible in the view
                model.addAttribute("songs", songsList);
                if (userStatus) {

                    return "premiumuserhome";
                } else {
                    return "nonpremiumuserhome";
                }
            }
        }
        return "userlogin";
    }


    @PostMapping("/resetpassword")
    public String resetPassword(@RequestParam String email, @RequestParam String oldPassword, @RequestParam String newPassword) {
        boolean isPasswordReset = userServiceImplementation.resetPassword(email, oldPassword, newPassword);

        if (isPasswordReset) {
            return "password_reset_success"; // Redirect to a success page or handle accordingly
        } else {
            return "password_reset_failure"; // Redirect to a failure page or handle accordingly
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "userlogin";
    }
}
