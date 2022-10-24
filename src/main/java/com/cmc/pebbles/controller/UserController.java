package com.cmc.pebbles.controller;

import com.cmc.pebbles.config.auth.PrincipalDetails;
import com.cmc.pebbles.domain.User;
import com.cmc.pebbles.dto.GetHomeRes;
import com.cmc.pebbles.dto.PostHighlightReq;
import com.cmc.pebbles.service.UserService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"유저 API 정보를 제공하는 Controller"})
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "홈 화면", notes = "로그인 된 유저에 해당하는 홈 화면을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "리턴 성공", response = GetHomeRes.class),
            @ApiResponse(code = 500, message = "서버 에러")

    })
    @GetMapping("/auth/home")
    public GetHomeRes home(Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        User user = principalDetails.getUser();
        return userService.home(user);
    }

    @ApiOperation(value = "추가 화면", notes = "유저는 highlight, habit, todo를 추가한다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "리턴 성공"),
            @ApiResponse(code = 500, message = "서버 에러")

    })
    @PostMapping("/auth/new")
    public String newHighlight(Authentication authentication, @RequestBody PostHighlightReq postHighlightReq) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        User user = principalDetails.getUser();
        userService.newHighlight(user, postHighlightReq);
        return "완료";
    }
}
