package com.cmc.pebbles.repository;

import com.cmc.pebbles.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    public List<Todo> findByHabitId(Long habit_id);
}
