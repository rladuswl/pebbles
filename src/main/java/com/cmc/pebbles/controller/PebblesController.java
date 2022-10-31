package com.cmc.pebbles.controller;

import com.cmc.pebbles.config.BaseException;
import com.cmc.pebbles.config.BaseResponse;
import com.cmc.pebbles.dto.GetHomeRes;
import com.cmc.pebbles.dto.PostHighlightReq;
import com.cmc.pebbles.service.PebblesService;
import com.cmc.pebbles.service.UserService;
import com.cmc.pebbles.utils.JwtService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.cmc.pebbles.config.BaseResponseStatus.INVALID_USER_JWT;

@Api(tags = {" 주요 API 기능을 제공하는 Controller"})
@RestController
@RequiredArgsConstructor
public class PebblesController {

    private final UserService userService;
    private final JwtService jwtService;
    private final PebblesService pebblesService;

    @ApiOperation(value = "홈 화면", notes = "로그인 된 유저에 해당하는 홈 화면을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "리턴 성공", response = GetHomeRes.class),
            @ApiResponse(code = 500, message = "서버 에러")

    })
    @GetMapping("/auth/home/{userId}")
    public BaseResponse<GetHomeRes> home(@PathVariable("userId") Long userId) {
        try {
            Long userIdxByJwt = jwtService.getUserId();
            if(userId != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
//            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
//            User user = principalDetails.getUser();
            GetHomeRes getHomeRes = pebblesService.home(userId);
            return new BaseResponse<>(getHomeRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }

    }

    @ApiOperation(value = "추가 화면", notes = "유저는 highlight, habit, todo를 추가한다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "리턴 성공"),
            @ApiResponse(code = 500, message = "서버 에러")

    })
    @PostMapping("/auth/new/{userId}")
    public BaseResponse<String> newHighlight(@PathVariable("userId") Long userId, @RequestBody PostHighlightReq postHighlightReq) {
//        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
//        User user = principalDetails.getUser();

        try {
            Long userIdxByJwt = jwtService.getUserId();
            if(userId != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
//            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
//            User user = principalDetails.getUser();
            pebblesService.newHighlight(userId, postHighlightReq);
            return new BaseResponse<>("완료");
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }


}
