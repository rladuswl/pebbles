package com.cmc.pebbles.repository;

import com.cmc.pebbles.domain.Highlight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HighlightRepository extends JpaRepository<Highlight, Long> {
    public List<Highlight> findByUserId(Long user_id);
}
