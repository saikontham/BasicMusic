package com.musicapplication.musicapplication.controllers;

import com.musicapplication.musicapplication.entities.Playlist;
import com.musicapplication.musicapplication.entities.Songs;
import com.musicapplication.musicapplication.services.PlaylistService;
import com.musicapplication.musicapplication.services.PlaylistServiceImplementation;
import com.musicapplication.musicapplication.services.SongServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
public class PlaylistController {
    // Autowired instances for PlaylistService, SongServiceImplementation, and PlaylistServiceImplementation

    @Autowired
    private PlaylistService playlistService;
    @Autowired
    private SongServiceImplementation songServiceImplementation;
    @Autowired
    PlaylistServiceImplementation playlistServiceImplementation;
    // Display the page for creating a new playlist

    @GetMapping("/createPlaylist")
    public String createPlaylist(Model model) {
        // Retrieve the list of available songs from the SongServiceImplementation

        List<Songs> songList= songServiceImplementation.displaySongs();
        // Add the list of songs to the model to make it accessible in the Thymeleaf template

        model.addAttribute("songs", songList);
        // Return the logical view name for rendering

        return "createPlaylist";
    }
    @PostMapping("/addPlaylist")
    public String addPlaylist(@ModelAttribute Playlist playlist) {
        // Step 1: Add the playlist
        playlistServiceImplementation.addplaylist(playlist);

        // Step 2: Update songs associated with the playlist
        List<Songs> songList = playlist.getSongs();
        for (Songs song : songList) {
            // Add the current playlist to the song's playlist collection
            song.getPlaylist().add(playlist);

            // Update the song in the system
            songServiceImplementation.updateSong(song);
            System.out.println("Received Playlist: " + playlist);

        }

        // Step 3: Return the logical view name for rendering
        return "adminhome";
    }
    // Display the page for viewing existing playlists

    @GetMapping("/viewPlaylist")
    public String viewPlaylist(Model model)
    {
        // Retrieve the list of existing playlists from the PlaylistServiceImplementation

        List<Playlist> playlists= playlistServiceImplementation.viewPlaylist();
        // Add the list of playlists to the model to make it accessible in the Thymeleaf template

        model.addAttribute("playlists",playlists);
        // Return the logical view name for rendering

        return "viewPlaylist";
    }

}
