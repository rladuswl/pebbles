package com.cmc.pebbles.controller;

import com.cmc.pebbles.config.BaseException;
import com.cmc.pebbles.config.BaseResponse;
import com.cmc.pebbles.dto.*;
import com.cmc.pebbles.service.PebblesService;
import com.cmc.pebbles.service.UserService;
import com.cmc.pebbles.utils.JwtService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cmc.pebbles.config.BaseResponseStatus.INVALID_USER_JWT;

@Api(tags = {" 주요 API 기능을 제공하는 Controller"})
@RequestMapping("/api")
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
    @GetMapping("/home/{userId}")
    public BaseResponse<GetHomeRes> home(@PathVariable("userId") Long userId) {
        try {
            Long userIdxByJwt = jwtService.getUserId();
            if(userId != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
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
    @PostMapping("/rock/manage/new/{userId}")
    public BaseResponse<String> newHighlight(@PathVariable("userId") Long userId, @RequestBody PostHighlightReq postHighlightReq) {
        try {
            Long userIdxByJwt = jwtService.getUserId();
            if(userId != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            pebblesService.newHighlight(userId, postHighlightReq);
            return new BaseResponse<>("완료");
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    // 홈 화면 갱신
    @ApiOperation(value = "홈 화면 갱신", notes = "하루가 지나면 홈 화면을 갱신한다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "리턴 성공"),
            @ApiResponse(code = 500, message = "서버 에러")

    })
    @PostMapping("/home/{userId}/update")
    public BaseResponse<String> updateHome(@PathVariable("userId") Long userId, @RequestBody List<UpdateHomeReq> updateHomeReqs) {
        try {
            Long userIdxByJwt = jwtService.getUserId();
            if(userId != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            pebblesService.updateHome(userId, updateHomeReqs);
            return new BaseResponse<>("완료");
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    // 홈 habit 날짜 바꾸기

    // 바윗돌 관리 페이지
    @ApiOperation(value = "바윗돌 관리", notes = "바윗돌 관리 카테고리에서 전체 바윗돌(Highlight)를 볼 수 있다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "리턴 성공", response = GetHomeRes.class),
            @ApiResponse(code = 500, message = "서버 에러")

    })
    @GetMapping("/rock/manage/{userId}")
    public BaseResponse<List<GetRockManageRes>> RockManage(@PathVariable("userId") Long userId) {
        try {
            Long userIdxByJwt = jwtService.getUserId();
            if(userId != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            List<GetRockManageRes> getRockManageResList = pebblesService.RockManage(userId);
            return new BaseResponse<>(getRockManageResList);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }

    }

    // 바윗돌 관리 상세 페이지
    @ApiOperation(value = "바윗돌 관리 상세페이지", notes = "바윗돌 관리에서 하나의 바윗돌을 선택하면 상세페이지로 이동한다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "리턴 성공", response = GetHomeRes.class),
            @ApiResponse(code = 500, message = "서버 에러")

    })
    @GetMapping("/rock/manage/{userId}/{highlight_id}")
    public BaseResponse<GetRockManageDetailRes> RockManageDetail(@PathVariable("userId") Long userId, @PathVariable("highlight_id") Long highlight_id) {
        try {
            Long userIdxByJwt = jwtService.getUserId();
            if(userId != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            GetRockManageDetailRes getRockManageDetailRes = pebblesService.RockManageDetail(userId, highlight_id);
            return new BaseResponse<>(getRockManageDetailRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }

    }

    // 목표 설정 완료

    // 내 돌탑

    // 내 돌탑 상세 페이지

}
