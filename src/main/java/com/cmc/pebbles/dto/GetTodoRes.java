package com.cmc.pebbles.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "todo 정보 (홈 응답 Dto)", description = "habit에 해당하는 모든 todo 정보")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetTodoRes {
    @ApiModelProperty(value = "todo ID")
    private Long id;
    @ApiModelProperty(value = "todo 이름")
    private String name;
    @ApiModelProperty(value = "todo 순서")
    private int seq;
    @ApiModelProperty(value = "완료 여부 (항상 false)")
    private String status;
}
