package com.cmc.pebbles.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel(value = "new habit 정보 (추가 api Dto)", description = "생성하려는 habit 데이터")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostHabitReq {
    @ApiModelProperty(value = "habit 이름")
    private String name;
    @ApiModelProperty(value = "habit 시작일")
    private String start;
    @ApiModelProperty(value = "habit 종료일")
    private String end;
    @ApiModelProperty(value = "habit 요일")
    private String weeks;
    @ApiModelProperty(value = "habit 순서")
    private int seq;
    @ApiModelProperty(value = "각 habit의 todo")
    private List<PostTodoReq> todos;
    @ApiModelProperty(value = "날짜 리스트")
    private List<String> days;
}
