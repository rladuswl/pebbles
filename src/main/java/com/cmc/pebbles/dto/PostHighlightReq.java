package com.cmc.pebbles.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel(value = "new highlight 정보", description = "생성하려는 highlight 데이터")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostHighlightReq {
    @ApiModelProperty(value = "highlight 이름")
    private String name;
    @ApiModelProperty(value = "highlight 시작일")
    private String start;
    @ApiModelProperty(value = "highlight 종료일")
    private String end;
    @ApiModelProperty(value = "highlight의 habit")
    private List<PostHabitReq> habits;
}
