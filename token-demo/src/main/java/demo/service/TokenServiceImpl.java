package demo.service;

import demo.util.JedisUtil;
import demo.util.ResponseCode;
import demo.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {
    private static final String TOKEN_NAME = "token";

    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public void checkToken(HttpServletRequest request) throws Exception {
        String token = request.getHeader(TOKEN_NAME);
        if (token==null || token.isEmpty()) {// header中不存在token
            token = request.getParameter(TOKEN_NAME);
            if (token==null || token.isEmpty()) {// parameter中也不存在token
                throw new Exception(ResponseCode.ILLEGAL_ARGUMENT.getMsg());
            }
        }

        if (!jedisUtil.exists(token)) {
            throw new Exception(ResponseCode.REPETITIVE_OPERATION.getMsg());
        }

        Long del = jedisUtil.del(token);
        if (del <= 0) {
            throw new Exception(ResponseCode.REPETITIVE_OPERATION.getMsg());
        }
    }

    @Override
    public ServerResponse createToken() {
        String str = UUID.randomUUID().toString();
        StringBuilder token = new StringBuilder();
        token.append(TOKEN_NAME).append(str);
        jedisUtil.set(token.toString(), token.toString(), 120);
        return ServerResponse.success(token.toString());

    }
}
