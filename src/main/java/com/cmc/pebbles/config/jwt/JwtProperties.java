package com.cmc.pebbles.config.jwt;

public interface JwtProperties {
    String SECRET = "harupuppyPebblesgkfntrkddkwldhsmfdmlwhdirehf";
    int EXPIRATION_TIME =  60000*14400; // 10일
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
