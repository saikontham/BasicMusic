package com.musicapplication.musicapplication.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Songs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String songName;
    private String album;
    private String artist;
    @ManyToMany
    private List<Playlist> playlist; // Assuming Playlist is another class
    private boolean isPremium;
    private String link;
    private String genre;

    @Override
    public String toString() {
        return "Songs{" +
                "id=" + id +
                ", songName='" + songName + '\'' +
                ", album='" + album + '\'' +
                ", artist='" + artist + '\'' +
                ", isPremium=" + isPremium +
                ", link='" + link + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}