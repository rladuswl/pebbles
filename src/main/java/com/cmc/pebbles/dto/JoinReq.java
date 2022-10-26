package com.cmc.pebbles.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "회원가입 정보", description = "유저 회원가입 정보")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JoinReq {
    @ApiModelProperty(value = "유저 닉네임")
    private String username;
    @ApiModelProperty(value = "유저 비밀번호")
    private String password;
    @ApiModelProperty(value = "목표")
    private String goal;
}
