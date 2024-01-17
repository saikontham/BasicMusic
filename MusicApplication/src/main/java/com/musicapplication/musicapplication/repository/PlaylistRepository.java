package com.musicapplication.musicapplication.repository;

import com.musicapplication.musicapplication.entities.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist,Integer> {
}
