package com.musicapplication.musicapplication.services;

import com.musicapplication.musicapplication.entities.Playlist;

import java.util.List;

public interface PlaylistService {
    public void addplaylist(Playlist playlist);

    public List<Playlist> viewPlaylist();
}
