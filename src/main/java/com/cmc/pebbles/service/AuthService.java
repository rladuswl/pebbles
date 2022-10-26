package com.cmc.pebbles.service;

import com.cmc.pebbles.config.BaseException;
import com.cmc.pebbles.domain.User;
import com.cmc.pebbles.dto.JoinReq;
import com.cmc.pebbles.dto.LoginReq;
import com.cmc.pebbles.dto.LoginRes;
import com.cmc.pebbles.repository.UserRepository;
import com.cmc.pebbles.utils.JwtService;
import com.cmc.pebbles.utils.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.cmc.pebbles.config.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public LoginRes login(LoginReq loginReq) throws BaseException {
        User user = userRepository.findByUsername(loginReq.getUsername());
        String encryptPwd;

        try{
            encryptPwd = new SHA256().encrypt(loginReq.getPassword());
        }
        catch (Exception exception) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }

        if (user.getPassword().equals(encryptPwd)) {
            Long userId = user.getId();
            String jwt = jwtService.createJwt(userId);
            return new LoginRes(userId, jwt);
        }
        else {
            throw new BaseException(FAILED_TO_LOGIN);
        }
    }

    public LoginRes createUser(JoinReq joinReq) throws BaseException {
        // 닉네임 중복 확인
        if(userRepository.existsByUsername(joinReq.getUsername())){
            throw new BaseException(DUPLICATED_EMAIL);
        }

        String pwd;
        try{
            //암호화
            pwd = new SHA256().encrypt(joinReq.getPassword());
            User user = User.builder()
                    .username(joinReq.getUsername())
                    .password(pwd)
                    .kakao_name(null)
                    .kakao_profile(null)
                    .email(null)
                    .goal(joinReq.getGoal())
                    .role("ROLE_USER")
                    .status("ACTIVE")
                    .build();
            userRepository.save(user);
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }
        try{
            //jwt 발급
            User user = userRepository.findByUsername(joinReq.getUsername());
            String jwt = jwtService.createJwt(user.getId());
            return new LoginRes(user.getId(), jwt);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
