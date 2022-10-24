package com.cmc.pebbles.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "new todo 정보 (추가 api Dto)", description = "생성하려는 todo 데이터")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostTodoReq {
    @ApiModelProperty(value = "todo 이름")
    private String name;
    @ApiModelProperty(value = "todo 순서")
    private int seq;
}
