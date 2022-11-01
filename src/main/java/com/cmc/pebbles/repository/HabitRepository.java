package com.cmc.pebbles.repository;

import com.cmc.pebbles.domain.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Long> {
    List<Habit> findByUserId(Long userId);
    List<Habit> findByHighlightId(Long highlightId);
}
