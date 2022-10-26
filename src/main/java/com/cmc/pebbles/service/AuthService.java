package com.cmc.pebbles.service;

import com.cmc.pebbles.config.BaseException;
import com.cmc.pebbles.domain.User;
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
}
