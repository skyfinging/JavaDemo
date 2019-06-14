package demo.controller;

import demo.service.TokenService;
import demo.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {
    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ServerResponse token() {
        return tokenService.createToken();
    }

}
