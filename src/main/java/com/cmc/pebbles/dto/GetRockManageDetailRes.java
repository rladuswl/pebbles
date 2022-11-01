package com.cmc.pebbles.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel(value = "바윗돌 관리 상세페이지 Dto")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetRockManageDetailRes {
    @ApiModelProperty(value = "바윗돌 id")
    private Long id;
    @ApiModelProperty(value = "시작일")
    private String start;
    @ApiModelProperty(value = "종료일")
    private String end;
    @ApiModelProperty(value = "바윗돌 제목")
    private String name;
    @ApiModelProperty(value = "조약돌 리스트")
    private List<GetRockManageDetailHabitRes> getRockManageDetailHabitResList;
}
