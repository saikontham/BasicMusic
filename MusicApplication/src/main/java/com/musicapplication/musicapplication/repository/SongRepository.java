package com.musicapplication.musicapplication.repository;

import com.musicapplication.musicapplication.entities.Songs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Songs,Integer> {
}
