package com.cmc.pebbles.controller;

import com.cmc.pebbles.config.BaseException;
import com.cmc.pebbles.config.BaseResponse;
import com.cmc.pebbles.dto.JoinReq;
import com.cmc.pebbles.dto.LoginReq;
import com.cmc.pebbles.dto.LoginRes;
import com.cmc.pebbles.service.AuthService;
import com.cmc.pebbles.utils.JwtService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@Api(tags = {"유저 회원가입, 로그인 Controller"})
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    private final JwtService jwtService;

    @ApiOperation(value = "로그인 화면", notes = "유저는 아이디와 비밀번호로 로그인을 한다.")
    @ResponseBody
    @PostMapping ("/login")
    public BaseResponse<LoginRes> login(@RequestBody LoginReq loginReq) {
        try{

            LoginRes loginRes = authService.login(loginReq);
            return new BaseResponse<>(loginRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ApiOperation(value = "회원가입 화면", notes = "유저는 아이디와 비밀번호, 목표를 입력하여 회원가입을 한다.")
    @ResponseBody
    @PostMapping("/join")
    public BaseResponse<LoginRes> createUser(@RequestBody JoinReq joinReq) {
        try{
            LoginRes loginRes = authService.createUser(joinReq);
            return new BaseResponse<>(loginRes);
        } catch(BaseException exception){
            return new BaseResponse((exception.getStatus()));
        }
    }

    @ApiOperation(value = "아이디 중복 확인", notes = "회원가입시 아이디 중복 확인을 한다.")
    @ResponseBody
    @PostMapping("/duplicate/username")
    public BaseResponse<Boolean> checkUserExist(@RequestParam String username) {
        try{
            Boolean result = authService.checkUserExist(username);
            return new BaseResponse<>(result);
        } catch(BaseException exception){
            return new BaseResponse((exception.getStatus()));
        }
    }
}















