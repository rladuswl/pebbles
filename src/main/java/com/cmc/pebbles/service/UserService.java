package com.cmc.pebbles.service;

import com.cmc.pebbles.domain.*;
import com.cmc.pebbles.dto.*;
import com.cmc.pebbles.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

//        List<Highlight> highlights = highlightRepository.findByUserId(user.getId());
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
        return "완료";
    }
}
