package com.musicapplication.musicapplication.services;

import com.musicapplication.musicapplication.entities.Playlist;
import com.musicapplication.musicapplication.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistServiceImplementation implements PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    /**
     * Adds a new playlist to the system by saving it through the PlaylistRepository.
     *
     * @param playlist The Playlist object representing the playlist to be added.
     *                 It contains details such as playlist name, songs, etc.
     */
    @Override
    public void addplaylist(Playlist playlist) {
        // Save the provided playlist using the PlaylistRepository

        playlistRepository.save(playlist);
    }

    /**
     * Retrieves and returns a list of all playlists available in the system.
     *
     * @return A List of Playlist objects representing all playlists in the system.
     *         The list is obtained by querying the PlaylistRepository for all playlists.
     */
    @Override
    public List<Playlist> viewPlaylist() {
        // Retrieve all playlists from the system using the PlaylistRepository

        return playlistRepository.findAll();
    }
}
