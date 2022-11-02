package com.cmc.pebbles.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@ApiModel(value = "내 돌탑 Highlight", description = "내 돌탑 페이지 Highlight 정보")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetMyStoneHighlightRes {
    @ApiModelProperty(value = "highlight ID")
    private Long id;
    @ApiModelProperty(value = "highlight 이름")
    private String name;
    @ApiModelProperty(value = "전체 조약돌에 대한 현재 조약돌 퍼센트")
    private Double pebbles_percent;
}
