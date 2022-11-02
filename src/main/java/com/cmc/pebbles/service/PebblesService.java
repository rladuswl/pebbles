package com.cmc.pebbles.service;

import com.cmc.pebbles.domain.*;
import com.cmc.pebbles.dto.*;
import com.cmc.pebbles.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
        }
        GetHomeRes getHomeRes = GetHomeRes.builder()
                .today(formatedNow)
                .habits(getHabitsResList).build();

        return getHomeRes;
    }

    @Transactional
    public PostNewRes newHighlight(Long userId, PostHighlightReq postHighlightReq) {
        Optional<User> user = userRepository.findById(userId);
        List<String> habits_name = new ArrayList<>();
        List<String> todos_name = new ArrayList<>();
        int highlight_total_pebbles = 0;

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
                    .total_pebbles(h.getDays().size())
                    .seq(h.getSeq())
                    .status("False")
                    .userId(user.get().getId())
                    .highlight(highlight).build();

            habitRepository.save(habit);
            habits_name.add(habit.getName());
            highlight_total_pebbles += h.getDays().size();

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
                todos_name.add(todo.getName());
            }
        }
        highlight.setTotal_pebbles(highlight_total_pebbles);
        highlightRepository.save(highlight);

        PostNewRes postNewRes = PostNewRes.builder()
                .highlight_name(highlight.getName())
                .habits_name(habits_name)
                .todos_name(todos_name).build();

        return postNewRes;
    }

    @Transactional
    public String updateHome(Long userId, List<UpdateHomeReq> updateHomeReqs) {
        for (UpdateHomeReq uh : updateHomeReqs) {
            Optional<DayHabit> dayHabit = dayHabitRepository.findById(uh.getId());
            dayHabit.get().setToday_status(uh.getStatus());
        }
        return "완료";
    }

    public List<GetRockManageRes> rockManage(Long userId) {
        List<GetRockManageRes> getRockManageResList = new ArrayList<>();
        List<Highlight> highlight = highlightRepository.findByUserId(userId);
        for (Highlight h : highlight) {
            GetRockManageRes getRockManageRes = GetRockManageRes.builder()
                    .id(h.getId())
                    .start(h.getStart())
                    .end(h.getEnd())
                    .name(h.getName()).build();
            getRockManageResList.add(getRockManageRes);
        }
        return getRockManageResList;
    }

    public GetRockManageDetailRes rockManageDetail(Long userId, Long highlight_id) {
        Optional<Highlight> highlight = highlightRepository.findById(highlight_id);
        List<GetRockManageDetailHabitRes> getRockManageDetailHabitResList = new ArrayList<>();

        List<Habit> habits = habitRepository.findByHighlightId(highlight.get().getId());

        for (Habit h : habits) {
            List<Todo> todos = todoRepository.findByHabitId(h.getId());
            List<GetRockManageDetailTodoRes> getRockManageDetailTodoResList = new ArrayList<>();

            for (Todo t : todos) {
                GetRockManageDetailTodoRes getRockManageDetailTodoRes = GetRockManageDetailTodoRes.builder()
                        .id(t.getId())
                        .name(t.getName())
                        .seq(t.getSeq()).build();
                getRockManageDetailTodoResList.add(getRockManageDetailTodoRes);
            }
            GetRockManageDetailHabitRes getRockManageDetailHabitRes = GetRockManageDetailHabitRes.builder()
                    .id(h.getId())
                    .name(h.getName())
                    .start(h.getStart())
                    .end(h.getEnd())
                    .weeks(h.getWeeks())
                    .seq(h.getSeq())
                    .getRockManageDetailTodoResList(getRockManageDetailTodoResList).build();
            getRockManageDetailHabitResList.add(getRockManageDetailHabitRes);
        }

        GetRockManageDetailRes getRockManageDetailRes = GetRockManageDetailRes.builder()
                .id(highlight.get().getId())
                .name(highlight.get().getName())
                .start(highlight.get().getStart())
                .end(highlight.get().getEnd())
                .getRockManageDetailHabitResList(getRockManageDetailHabitResList).build();

        return getRockManageDetailRes;
    }

    @Transactional
    public String updateHomeHabit(Long userId, PostUpdateHomeHabitReq postUpdateHomeHabitReq) {
        Optional<DayHabit> dayHabit = dayHabitRepository.findById(postUpdateHomeHabitReq.getId());
        dayHabit.get().setToday(postUpdateHomeHabitReq.getDay());
        return "완료";
    }

    public GetMyStoneRes myStoneTower(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        List<Highlight> highlights = highlightRepository.findByUserId(userId);
        List<GetMyStoneHighlightRes> getMyStoneHighlightResList = new ArrayList<>();

        for (Highlight h : highlights) {
            int current_pebbles = 0;
            double pebbles_percent = 0.0;

            List<Habit> habits = habitRepository.findByHighlightId(h.getId());

            for (Habit hb : habits) {
                current_pebbles += hb.getCurrent_pebbles();
            }

            pebbles_percent = Math.round(((current_pebbles/(double)h.getTotal_pebbles()) * 100) * 100) / 100.0;

            GetMyStoneHighlightRes getMyStoneHighlightRes = GetMyStoneHighlightRes.builder()
                    .id(h.getId())
                    .name(h.getName())
                    .pebbles_percent(pebbles_percent).build();

            getMyStoneHighlightResList.add(getMyStoneHighlightRes);
        }

        GetMyStoneRes getMyStoneRes = GetMyStoneRes.builder()
                .goal(user.get().getGoal())
                .getMyStoneHighlightRes(getMyStoneHighlightResList).build();

        return getMyStoneRes;
    }
}
