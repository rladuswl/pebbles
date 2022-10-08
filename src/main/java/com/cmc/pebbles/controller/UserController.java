package com.cmc.pebbles.controller;

import com.cmc.pebbles.config.auth.PrincipalDetails;
import com.cmc.pebbles.domain.User;
import com.cmc.pebbles.dto.GetHomeRes;
import com.cmc.pebbles.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"유저 API 정보를 제공하는 Controller"})
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "홈 화면", notes = "로그인 된 유저에 해당하는 홈 화면을 조회한다.")
    @GetMapping("/auth/home")
    public GetHomeRes home(Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        User user = principalDetails.getUser();
        return userService.home(user);
    }
}
