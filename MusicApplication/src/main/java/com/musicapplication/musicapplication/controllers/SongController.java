package com.musicapplication.musicapplication.controllers;

import com.musicapplication.musicapplication.entities.Songs;
import com.musicapplication.musicapplication.services.SongServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SongController {
    // Autowired instance for SongServiceImplementation

    @Autowired
    private SongServiceImplementation songServiceImplementation;
    // Process the form submission to add a new song

    @PostMapping("/addsong")
    public String addSong(@ModelAttribute Songs songs)
    {
        // Step 1: Add the new song to the system using SongServiceImplementation

        songServiceImplementation.addSong(songs);
        // Step 2: Return the logical view name for rendering (redirect to addsongs in this case)

        return "addsongs";
    }
    // Display the page for viewing all available songs

    @GetMapping("/displaysongs")
    public String displaySongs(Model model)
    {
        // Step 1: Retrieve the list of available songs from the SongServiceImplementation

        List<Songs> songsList=songServiceImplementation.displaySongs();
        // Step 2: Add the list of songs to the model to make it accessible in the Thymeleaf template

        model.addAttribute("songs",songsList);
        // Step 3: Return the logical view name for rendering

        return "displaysongs";
    }

    //add delete song functionality
}
