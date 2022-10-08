package com.cmc.pebbles.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;

@ApiModel(value = "todo 정보", description = "habit에 해당하는 모든 todo 정보")
@Builder
public class GetTodoRes {
    @ApiModelProperty(value = "todo ID")
    private Long id;
    @ApiModelProperty(value = "todo 이름")
    private String name;
    @ApiModelProperty(value = "todo 순서")
    private int seq;
    @ApiModelProperty(value = "todo 전부 완료 여부")
    private String status;
}
