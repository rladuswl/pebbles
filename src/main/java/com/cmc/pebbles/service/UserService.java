package com.cmc.pebbles.service;

import com.cmc.pebbles.domain.*;
import com.cmc.pebbles.dto.*;
import com.cmc.pebbles.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final HighlightRepository highlightRepository;
    private final HabitRepository habitRepository;
    private final TodoRepository todoRepository;
    private final DayHabitRepository dayHabitRepository;

    public GetHomeRes home(User user) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatedNow = now.format(formatter);

        List<GetHabitsRes> getHabitsResList = new ArrayList<>();
        List<GetTodoRes> getTodoResList = new ArrayList<>();

        List<Habit> habits = habitRepository.findByUserId(user.getId());

        for (Habit h : habits) {
            List<Todo> todos = todoRepository.findByHabitId(h.getId());

            for (Todo t : todos) {
                GetTodoRes getTodoRes = GetTodoRes.builder()
                        .id(t.getId())
                        .name(t.getName())
                        .seq(t.getSeq())
                        .status(t.getStatus())
                        .build();
                getTodoResList.add(getTodoRes);
            }

            List<DayHabit> dayHabits = dayHabitRepository.findByHabitId(h.getId());

            for (DayHabit dh : dayHabits) {
                GetHabitsRes getHabitsRes = GetHabitsRes.builder()
                        .id(h.getId())
                        .name(h.getName())
                        .start(h.getStart())
                        .end(h.getEnd())
                        .weeks(h.getWeeks())
                        .today(dh.getToday())
                        .cons_days(h.getCons_days())
                        .seq(h.getSeq())
                        .today_status(dh.getToday_status())
                        .status(h.getStatus())
                        .todos(getTodoResList)
                        .build();
                getHabitsResList.add(getHabitsRes);
            }
            getTodoResList.clear();
        }
        GetHomeRes getHomeRes = GetHomeRes.builder()
                .today(formatedNow)
                .habits(getHabitsResList).build();

        return getHomeRes;
    }

    public String newHighlight(User user, PostHighlightReq postHighlightReq) {
        Highlight highlight = Highlight.builder()
                .name(postHighlightReq.getName())
                .start(postHighlightReq.getStart())
                .end(postHighlightReq.getEnd())
                .total_pebbles(0)
                .status("False")
                .user(user)
                .build();

        List<PostHabitReq> habits = postHighlightReq.getHabits();
        for (PostHabitReq h : habits) {
            Habit habit = Habit.builder()
                    .name(h.getName())
                    .start(h.getStart())
                    .end(h.getEnd())
                    .weeks(h.getWeeks())
                    .cons_days(0)
                    .current_pebbles(0)
                    .total_pebbles(0)
                    .seq(h.getSeq())
                    .status("False")
                    .userId(user.getId()).build();

            habitRepository.save(habit);

            List<String> days = h.getDays();
            for (String day : days) {
                DayHabit dayHabit = DayHabit.builder()
                        .today(day)
                        .today_status("False").build();
                dayHabitRepository.save(dayHabit);
            }

            List<PostTodoReq> todos = h.getTodos();
            for (PostTodoReq t : todos) {
                Todo todo = Todo.builder()
                        .name(t.getName())
                        .seq(t.getSeq())
                        .status("False").build();
                todoRepository.save(todo);

            }
        }
        highlightRepository.save(highlight);

        return "완료";
    }
}
