package com.cmc.pebbles.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel(value = "홈 응답 정보")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetHomeRes {
    @ApiModelProperty(value = "오늘 날짜")
    private String today;
    @ApiModelProperty(value = "날짜 별 habits")
    private List<GetHabitsRes> habits;
}
