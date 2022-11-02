package com.cmc.pebbles.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel(value = "내 돌탑 상세페이지 habit 리스트", description = "내 돌탑 상세페이지 habit 리스트 응답 정보")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetMyStoneHabitRes {
    @ApiModelProperty(value = "habit 이름")
    private String name;
    @ApiModelProperty(value = "habit 시작일")
    private String start;
    @ApiModelProperty(value = "habit 종료일")
    private String end;
    @ApiModelProperty(value = "habit 전체 조약돌")
    private int total_pebbles;
    @ApiModelProperty(value = "habit 현재 조약돌")
    private int current_pebbles;
    @ApiModelProperty(value = "todo 리스트")
    private List<String> todos_name;
}
