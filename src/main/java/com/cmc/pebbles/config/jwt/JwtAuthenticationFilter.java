//package com.cmc.pebbles.config.jwt;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.cmc.pebbles.config.auth.PrincipalDetails;
//import com.cmc.pebbles.dto.LoginReq;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Date;
//
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    private final AuthenticationManager authenticationManager;
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//            throws AuthenticationException {
//
//        ObjectMapper om = new ObjectMapper();
//        LoginReq loginRequestDto = null;
//        try{
//            loginRequestDto = om.readValue(request.getInputStream(), LoginReq.class);
//
//        } catch(IOException e){
//            e.printStackTrace();
//        }
//
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), "");
//
//        Authentication authentication =
//                authenticationManager.authenticate(authenticationToken);
//
//        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
//
//        return authentication;
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
//                                            Authentication authResult) throws IOException, ServletException {
//        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
//
//        String jwtToken = JWT.create()
//                .withSubject(principalDetails.getUsername())
//                .withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
//                .withClaim("id", principalDetails.getUser().getId())
//                .withClaim("username", principalDetails.getUser().getUsername())
//                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
//
//        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX+jwtToken);
//    }
//}
