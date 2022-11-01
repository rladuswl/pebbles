package com.cmc.pebbles.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel(value = "update Home 정보", description = "홈 화면 갱신시 요청 데이터")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateHomeReq {
    @ApiModelProperty(value = "오늘 날짜")
    private String today;
    @ApiModelProperty(value = "habit id")
    private Long id;
    @ApiModelProperty(value = "성공 여부")
    private String status;
}
