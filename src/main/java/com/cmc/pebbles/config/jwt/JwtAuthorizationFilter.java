//package com.cmc.pebbles.config.jwt;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.auth0.jwt.exceptions.TokenExpiredException;
//import com.cmc.pebbles.config.auth.PrincipalDetails;
//import com.cmc.pebbles.domain.User;
//import com.cmc.pebbles.repository.UserRepository;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Optional;
//
//public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
//
//    private UserRepository userRepository;
//
//    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
//        super(authenticationManager);
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//
//        String jwtHeader = request.getHeader(JwtProperties.HEADER_STRING);
//        System.out.println("jwtHeader : " + jwtHeader);
//
//        if (jwtHeader == null || !jwtHeader.startsWith(JwtProperties.TOKEN_PREFIX)) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        String jwtToken = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");
//
////        Long userId = null;
////
////        try {
////               userId = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(jwtToken)
////                       .getClaim("id").asLong();
////
////            Optional<User> user = userRepository.findById(userId);
////
////           PrincipalDetails principalDetails = new PrincipalDetails(user.get());
////
////           Authentication authentication =
////                   new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
////
////           SecurityContextHolder.getContext().setAuthentication(authentication);
////
////        } catch (TokenExpiredException e) {
////           e.printStackTrace();
////           request.setAttribute(JwtProperties.HEADER_STRING, "토큰이 만료되었습니다.");
////        } catch (JWTVerificationException e) {
////           e.printStackTrace();
////           request.setAttribute(JwtProperties.HEADER_STRING, "유효하지 않은 토큰입니다.");
////        }
////
////        request.setAttribute("id", userId);
////
////        chain.doFilter(request, response);
////    }
//
//        String username =
//                JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(jwtToken)
//                        .getClaim("email").asString();
//
//        if (username != null) {
//            User user = userRepository.findByUsername(username);
//
//            PrincipalDetails principalDetails = new PrincipalDetails(user);
//
//            Authentication authentication =
//                    new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            chain.doFilter(request, response);
//        }
//    }
//}