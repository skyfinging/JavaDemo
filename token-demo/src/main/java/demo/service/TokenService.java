package demo.service;

import demo.util.ServerResponse;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {
    void checkToken(HttpServletRequest request) throws Exception;

    ServerResponse createToken();
}
