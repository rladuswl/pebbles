package com.cmc.pebbles.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel(value = "홈 화면 habit 날짜 변경", description = "홈 화면 habit 날짜 바꾸기")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostUpdateHomeHabitReq {
    @ApiModelProperty(value = "habit ID")
    private Long id;
    @ApiModelProperty(value = "변경한 날짜")
    private String day;
}
