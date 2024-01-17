package com.musicapplication.musicapplication.services;


import com.musicapplication.musicapplication.entities.Songs;

import java.util.List;

public interface SongService {
    public void updateSong(Songs songs);
    public void addSong(Songs songs);
    public List<Songs> displaySongs();
}
