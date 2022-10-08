package com.cmc.pebbles.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;

import java.util.List;

@ApiModel(value = "habit 정보", description = "해당 유저의 모든 habit 정보")
@Builder
public class GetHabitsRes {
    @ApiModelProperty(value = "habit ID")
    private Long id;
    @ApiModelProperty(value = "habit 이름")
    private String name;
    @ApiModelProperty(value = "habit 시작일")
    private String start;
    @ApiModelProperty(value = "habit 종료일")
    private String end;
    @ApiModelProperty(value = "habit 요일")
    private String weeks;
    @ApiModelProperty(value = "habit 시간")
    private String time;
    @ApiModelProperty(value = "habit 실천하는 날짜")
    private String today;
    @ApiModelProperty(value = "연속 일수")
    private int cons_days;
    @ApiModelProperty(value = "habit 순서")
    private int seq;
    @ApiModelProperty(value = "habit 실천하는 날짜의 완료 여부")
    private String today_status;
    @ApiModelProperty(value = "완료 여부")
    private String status;
    @ApiModelProperty(value = "habit의 todos")
    private List<GetTodoRes> todos;
}
