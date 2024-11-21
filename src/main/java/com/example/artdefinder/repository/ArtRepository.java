package com.example.artdefinder.repository;

import com.example.artdefinder.entity.ArtEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtRepository extends JpaRepository<ArtEntity, Long> {
}
