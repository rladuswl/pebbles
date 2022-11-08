package com.cmc.pebbles.service;

import com.cmc.pebbles.domain.DayHabit;
import com.cmc.pebbles.domain.User;
import com.cmc.pebbles.dto.PostUpdateHomeHabitReq;
import com.cmc.pebbles.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public String outUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        user.get().setStatus("INACTIVE");
        userRepository.save(user.get());
        return "완료";
    }
}
