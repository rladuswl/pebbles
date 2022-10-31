//package com.cmc.pebbles.config;
//
//import com.cmc.pebbles.config.jwt.CustomAuthenticationEntryPoint;
//import com.cmc.pebbles.config.jwt.JwtAuthenticationFilter;
//import com.cmc.pebbles.config.jwt.JwtAuthorizationFilter;
//import com.cmc.pebbles.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.filter.CorsFilter;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    private final CorsFilter corsFilter;
//    private final UserRepository userRepository;
//
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http.csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .formLogin().disable()
//                .httpBasic().disable()
//                .apply(new CustomFilter())
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/aut/**")
//                .access("hasRole('ROLE_USER')")
//                .anyRequest().permitAll()
//                .and()
////                .exceptionHandling()
////                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
////                .and()
//                .build();
//    }
//
//    public class CustomFilter extends AbstractHttpConfigurer<CustomFilter, HttpSecurity> {
//        @Override
//        public void configure(HttpSecurity http) throws Exception {
//            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
//            http
//                    .addFilter(corsFilter)
//                    .addFilter(new JwtAuthenticationFilter(authenticationManager))
//                    .addFilter(new JwtAuthorizationFilter(authenticationManager, userRepository));
//        }
//    }
//}
