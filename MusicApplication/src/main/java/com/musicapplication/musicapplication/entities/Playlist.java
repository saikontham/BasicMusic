package com.musicapplication.musicapplication.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity

public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer playlistId;
    private String playlistName;
    @ManyToMany
    private List<Songs> songs;

    public Playlist() {
    }

    public Playlist(Integer playlistId, String playlistName, List<Songs> songs) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.songs = songs;
    }

    public Integer getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(Integer playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public List<Songs> getSongs() {
        return songs;
    }

    public void setSongs(List<Songs> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "playlistId=" + playlistId +
                ", playlistName='" + playlistName + '\'' +
                ", songs=" + songs +
                '}';
    }
}
