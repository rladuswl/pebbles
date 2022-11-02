package com.cmc.pebbles.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@ApiModel(value = "내 돌탑", description = "내 돌탑 페이지 정보")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetMyStoneRes {
    @ApiModelProperty(value = "내 목표")
    private String goal;
    @ApiModelProperty(value = "highlight 리스트 정보")
    private List<GetMyStoneHighlightRes> getMyStoneHighlightRes;
}
