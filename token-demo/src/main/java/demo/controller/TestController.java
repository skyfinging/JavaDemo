package demo.controller;

import demo.annotation.ApiIdempotent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @ApiIdempotent
    @RequestMapping("/test")
    public String abc(){
        return "test";
    }
}
