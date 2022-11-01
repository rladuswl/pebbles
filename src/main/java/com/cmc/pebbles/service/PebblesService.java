package com.cmc.pebbles.service;

import com.cmc.pebbles.domain.*;
import com.cmc.pebbles.dto.*;
import com.cmc.pebbles.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PebblesService {

    private final UserRepository userRepository;
    private final HighlightRepository highlightRepository;
    private final HabitRepository habitRepository;
    private final TodoRepository todoRepository;
    private final DayHabitRepository dayHabitRepository;

    public GetHomeRes home(Long useId) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatedNow = now.format(formatter);

        List<GetHabitsRes> getHabitsResList = new ArrayList<>();

        List<Habit> habits = habitRepository.findByUserId(useId);

        for (Habit h : habits) {
            List<GetTodoRes> getTodoResList = new ArrayList<>();
            List<Todo> todos = todoRepository.findByHabitId(h.getId());

            for (Todo t : todos) {
                System.out.println("todos" + t.getName());
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
                        .id(dh.getId())
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
            System.out.println("dayhabit " + getTodoResList.get(0).getName());
            System.out.println("habit안에 todo 잘 들어감 ???????????   " + getHabitsResList.get(0).getTodos().get(0).getName());
            //getTodoResList.clear();
            System.out.println("habit안에 todo 잘 들어감 ???????????!!!!!!!!!!   " + getHabitsResList.get(0).getTodos().get(0).getName());
        }
        GetHomeRes getHomeRes = GetHomeRes.builder()
                .today(formatedNow)
                .habits(getHabitsResList).build();

        System.out.println("확인   " + getHomeRes.getHabits().get(0).getTodos().get(0).getName());

        return getHomeRes;
    }

    @Transactional
    public String newHighlight(Long userId, PostHighlightReq postHighlightReq) {
        Optional<User> user = userRepository.findById(userId);

        Highlight highlight = Highlight.builder()
                .name(postHighlightReq.getName())
                .start(postHighlightReq.getStart())
                .end(postHighlightReq.getEnd())
                .total_pebbles(0)
                .status("False")
                .user(user.get())
                .build();

        highlightRepository.save(highlight);

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
                    .userId(user.get().getId())
                    .highlight(highlight).build();

            habitRepository.save(habit);

            List<String> days = h.getDays();
            for (String day : days) {
                DayHabit dayHabit = DayHabit.builder()
                        .today(day)
                        .today_status("False")
                        .habit(habit).build();
                dayHabitRepository.save(dayHabit);
            }

            List<PostTodoReq> todos = h.getTodos();
            for (PostTodoReq t : todos) {
                Todo todo = Todo.builder()
                        .name(t.getName())
                        .seq(t.getSeq())
                        .status("False")
                        .habit(habit).build();
                todoRepository.save(todo);

            }
        }
        return "완료";
    }

    @Transactional
    public String updateHome(Long userId, List<UpdateHomeReq> updateHomeReqs) {
        for (UpdateHomeReq uh : updateHomeReqs) {
            Optional<DayHabit> dayHabit = dayHabitRepository.findById(uh.getId());
            dayHabit.get().setToday_status(uh.getStatus());
        }
        return "완료";
    }
}