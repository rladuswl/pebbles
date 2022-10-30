package com.cmc.pebbles.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cmc.pebbles.config.BaseException;
import com.cmc.pebbles.config.jwt.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static com.cmc.pebbles.config.BaseResponseStatus.*;

@Service
public class JwtService {

    /*
    JWT 생성
    @param userIdx
    @return String
     */
    public String createJwt(Long userId){
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam("type","jwt")
                .claim("userId",userId)
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, JwtProperties.SECRET)
                .compact();
    }

    /*
    Header에서 X-ACCESS-TOKEN 으로 JWT 추출
    @return String
     */
    public String getJwt(){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader("X-ACCESS-TOKEN");
    }

    /*
    JWT에서 userId 추출
    @return Long
    @throws BaseException
     */
    public Long getUserId() throws BaseException{
        //1. JWT 추출
        String accessToken = getJwt();
        if(accessToken == null || accessToken.length() == 0){
            throw new BaseException(EMPTY_JWT);
        }

        // 2. JWT parsing
        Jws<Claims> claims;
//        try{
////            claims = Jwts.parser()
////                    .setSigningKey(JwtProperties.SECRET)
////                    .parseClaimsJws(accessToken);
//            claims = Jwts.parserBuilder()
//                    .setSigningKey(JwtProperties.SECRET)
//                    .build()
//                    .parseClaimsJws(accessToken);
//            System.out.println("##################################" + claims);
//        } catch (Exception ignored) {
//            throw new BaseException(INVALID_JWT);
//        }
        claims = Jwts.parserBuilder()
                    .setSigningKey(JwtProperties.SECRET)
                    .build()
                    .parseClaimsJws(accessToken);
            System.out.println("##################################" + claims);

        // 3. userId 추출
        return claims.getBody().get("userId",Long.class);
    }

}
