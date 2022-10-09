package com.cmc.pebbles.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "habit")
public class Habit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String start;
    private String end;
    private String weeks;
    private String time;
    private String today;
    private int cons_days;
    private int current_pebbles;
    private int total_pebbles;
    private int seq;
    private String today_status;
    private String status;

    @ManyToOne
    @JoinColumn(name = "highlight_id")
    private Highlight highlight;

    @OneToMany(mappedBy = "habit", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DayHabit> dayHabits;

    @OneToMany(mappedBy = "habit", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Todo> todos;
}