package com.musicapplication.musicapplication.services;

import com.musicapplication.musicapplication.entities.Songs;
import com.musicapplication.musicapplication.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImplementation implements SongService{

    @Autowired
    private SongRepository songRepository;

    /**
     * Adds a new song to the system by saving it through the SongRepository.
     *
     * @param songs The Songs object representing the song to be added.
     *              It contains details such as song name, artist, link, etc.
     */
    @Override
    public void addSong(Songs songs) {
        // Save the provided song using the SongRepository
        songRepository.save(songs);
    }

    /**
     * Retrieves and returns a list of all songs available in the system.
     *
     * @return A List of Songs objects representing all songs in the system.
     *         The list is obtained by querying the SongRepository for all songs.
     */
    @Override
    public List<Songs> displaySongs() {
        // Retrieve all songs from the system using the SongRepository
        List<Songs> songsList = songRepository.findAll();
        return songsList;
    }

    /**
     * Updates an existing song in the system by saving the updated information through the SongRepository.
     *
     * @param songs The Songs object representing the updated information of the song.
     *              It should have the same ID as the existing song to be updated.
     */
    @Override
    public void updateSong(Songs songs) {
        // Save the updated information of the song using the SongRepository
        songRepository.save(songs);
    }


}

