package com.cmc.pebbles.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel(value = "내 돌탑 상세페이지", description = "내 돌탑 상세페이지 응답 정보")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetMyStoneDetailRes {
    @ApiModelProperty(value = "highlight 이름")
    private String name;
    @ApiModelProperty(value = "highlight 시작일")
    private String start;
    @ApiModelProperty(value = "highlight 종료일")
    private String end;
    @ApiModelProperty(value = "highlight 실천율 (현재 조약돌/전체 조약돌)")
    private Double pebbles_percent;
    @ApiModelProperty(value = "habit 리스트")
    private List<GetMyStoneHabitRes> getMyStoneHabitResList;
}
