package demo.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class MyConfig {
    @Autowired
    @Getter
    private StringRedisTemplate redisTemplate;

    public static final String SCORE_RANK = "score_rank";
}
