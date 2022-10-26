package com.cmc.pebbles.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "로그인 응답", description = "로그인 완료 후 응답")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRes {
    @ApiModelProperty(value = "유저 id")
    private Long userId;
    @ApiModelProperty(value = "jwt 토큰")
    private String jwt;
}
