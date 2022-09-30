package com.cmc.pebbles;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String test() throws Exception{
        return "test(테스트)";
    }
}
