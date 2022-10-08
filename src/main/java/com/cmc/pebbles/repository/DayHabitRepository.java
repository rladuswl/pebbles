package com.cmc.pebbles.repository;

import com.cmc.pebbles.domain.DayHabit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DayHabitRepository extends JpaRepository<DayHabit, Long> {
    public List<DayHabit> findByHabitId(Long habit_id);
}
