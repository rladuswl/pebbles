package com.cmc.pebbles.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel(value = "바윗돌 추가 응답 Dto (목표 설정 완료)", description = "바윗돌 추가 완료 후 응답")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostNewRes {
    @ApiModelProperty(value = "highlight name")
    private String highlight_name;
    @ApiModelProperty(value = "habit name")
    private List<String> habits_name;
    @ApiModelProperty(value = "todo name")
    private List<String> todos_name;
}
