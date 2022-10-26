package com.cmc.pebbles.controller;

import com.cmc.pebbles.config.BaseException;
import com.cmc.pebbles.config.BaseResponse;
import com.cmc.pebbles.dto.JoinReq;
import com.cmc.pebbles.service.AuthService;
import com.cmc.pebbles.utils.JwtService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@Api(tags = {"유저 회원가입, 로그인 Controller"})
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    private final JwtService jwtService;

    @ResponseBody
    @PostMapping ("/login")
    public BaseResponse<PostLoginRes> login(@RequestBody PostLoginReq postLoginReq) {
        try{
            if (postLoginReq.getEmail() == null) {
                return new BaseResponse<>(POST_USERS_EMPTY_EMAIL);
            }

            if (postLoginReq.getPwd() == null) {
                return new BaseResponse<>(POST_USERS_EMPTY_PASSWORD);
            }

            // 정규식 검증 (이메일 형식, 비밀번호 형식이 맞는지)
            if (!isRegexEmail(postLoginReq.getEmail())) {
                return new BaseResponse<>(POST_USERS_INVALID_EMAIL);
            }

            PostLoginRes postLoginRes = authService.login(postLoginReq);
            return new BaseResponse<>(postLoginRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @PostMapping("/join")
    public BaseResponse<String> createUser(@RequestBody JoinReq joinReq) {
        try{
            authService.createUser(postUserReq);
            return new BaseResponse<>(postUserRes);
        } catch(BaseException exception){
            return new BaseResponse((exception.getStatus()));
        }
    }
}















