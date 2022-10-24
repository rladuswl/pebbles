package com.cmc.pebbles.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "day_habit")
public class DayHabit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String today;
    private String today_status;

    @ManyToOne
    @JoinColumn(name = "habit_id")
    private Habit habit;
}
