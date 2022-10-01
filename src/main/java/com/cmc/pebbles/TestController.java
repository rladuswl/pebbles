package com.cmc.pebbles;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    // beanstalk 배포
    @GetMapping("/")
    public String test() {
        return "하룻강아지 안뇽";
    }

    // test
    @GetMapping("/name")
    public String test2(@RequestParam String name) {
        return "hi " + name;
    }
}
