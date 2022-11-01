package com.cmc.pebbles.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "바윗돌 관리 상세페이지 모래알 Dto")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetRockManageDetailTodoRes {
    @ApiModelProperty(value = "모래알 id")
    private Long id;
    @ApiModelProperty(value = "모래알 이름")
    private String name;
    @ApiModelProperty(value = "모래알 순서")
    private int seq;
}
