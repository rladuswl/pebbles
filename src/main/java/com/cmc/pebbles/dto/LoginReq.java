package com.cmc.pebbles.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel(value = "로그인 정보", description = "유저 로그인 정보")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginReq {
    @ApiModelProperty(value = "유저 닉네임")
    private String username;
}
