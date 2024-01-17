package com.musicapplication.musicapplication.services;

import com.musicapplication.musicapplication.entities.User;
import com.musicapplication.musicapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService{
    @Autowired
    private UserRepository userRepository;

    /**
     * Adds a new user to the system by saving it through the UserRepository.
     *
     * @param user The User object representing the user to be added.
     *             It contains details such as email, password, and role.
     */
    @Override
    public void addUsers(User user) {
        // Save the provided user using the UserRepository
        userRepository.save(user);
    }

    /**
     * Checks whether a user with the given email already exists in the system.
     *
     * @param email The email to be checked for existence.
     * @return True if a user with the provided email exists, false otherwise.
     */
    @Override
    public boolean isEmailExists(String email) {
        // Find a user with the provided email using the UserRepository
        User user = userRepository.findByEmail(email);
        return user != null && user.getEmail().equals(email);
    }

    /**
     * Retrieves and returns the role of the user with the given email.
     *
     * @param email The email of the user whose role is to be retrieved.
     * @return The role of the user if found, or null if the user does not exist or has no role.
     */
    @Override
    public String validateRole(String email) {
        // Find a user with the provided email using the UserRepository
        User user = userRepository.findByEmail(email);

        if (user != null) {
            // Retrieve and return the role of the user (converted to lowercase for consistency)
            String role = user.getRole();
            return role != null ? role.toLowerCase() : null;
        }

        return null; // User not found
    }

    /**
     * Validates a user's login by checking the provided email and password.
     *
     * @param email The email of the user trying to log in.
     * @param password The password provided for login.
     * @return True if the login is valid, false otherwise.
     */
    @Override
    public boolean validateLogin(String email, String password) {
        // Find a user with the provided email using the UserRepository
        User user = userRepository.findByEmail(email);

        // Check if the user with the provided email exists
        if (user != null) {
            // Retrieve the stored password from the user
            String dbPassword = user.getPassword();

            // Use equals to compare passwords (consider using a secure password hashing mechanism)
            return password.equals(dbPassword);
        }

        // User with the provided email does not exist
        return false;
    }

    /**
     * Resets the password for a user with the provided email.
     *
     * @param email The email of the user whose password is to be reset.
     * @param oldPassword The old password for verification.
     * @param newPassword The new password to be set.
     * @return True if the password reset is successful, false otherwise.
     */
    @Override
    public boolean resetPassword(String email, String oldPassword, String newPassword) {
        // Find a user with the provided email using the UserRepository
        User user = userRepository.findByEmail(email);

        // Ensure user and old password match
        if (user != null && user.getPassword().equals(oldPassword)) {
            // Set the new password
            user.setPassword(newPassword);

            // Save the updated user to the database
            userRepository.save(user);

            return true; // Password reset success
        }

        return false; // Password reset failure
    }

    /**
     * Retrieves and returns a user with the given email.
     *
     * @param email The email of the user to be retrieved.
     * @return The User object representing the user with the provided email.
     */
    @Override
    public User getUser(String email) {
        // Find a user with the provided email using the UserRepository
        return userRepository.findByEmail(email);
    }

    /**
     * Updates an existing user in the system by saving the updated information through the UserRepository.
     *
     * @param user The User object representing the updated information of the user.
     */
    @Override
    public void updateUser(User user) {
        // Save the updated information of the user using the UserRepository
        userRepository.save(user);
    }


}
