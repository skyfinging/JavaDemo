package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Test1 {
    @Autowired
    private RedisTemplate redisTemplate;

    public static String KEY_NAME = "Login_";

    /**
     * 测试插入10万用户指定时间段的登陆情况
     */
    @Test
    public void test(){
        redisTemplate.setEnableTransactionSupport(true);
        LocalDate localDate = LocalDate.of(2019,1,1);
        LocalDate endDate = LocalDate.of(2019,2,1);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        int users = 100000;
        long b = System.currentTimeMillis();
        while(localDate.isBefore(endDate)){
            String key = KEY_NAME+dateTimeFormatter.format(localDate);
            ValueOperations valueOperations = redisTemplate.opsForValue();
            Random random = new Random();
            redisTemplate.multi();
            for(int i=0;i<users;i++){
                boolean isLogin = random.nextBoolean();
                if(isLogin)
                    valueOperations.setBit(key, i, true);
            }
            redisTemplate.exec();
            System.out.println(key+"完成插入");
            localDate = localDate.plusDays(1);
        }
        long e = System.currentTimeMillis();
        System.out.println("插入10万用户一年的登陆情况，花费："+(e-b));
    }

    public long bitCount(final String key) {
        return bitCount(key, 0, -1);
    }

    public Long bitCount(String key, int start, int end) {
        return (Long) redisTemplate.execute((RedisCallback<Long>) con -> con.bitCount(key.getBytes(), start, end));
    }

    /**
     * 获取某一天用户登陆的人数
     */
    @Test
    public void test1(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Set<String> keys = redisTemplate.keys("Login_*");
        for(String key : keys) {
            Long count = bitCount(key);
            System.out.println(key+" 登陆人数:"+count);
        }
    }

    /**
     * 某个用户的登陆情况
     */
    @Test
    public void test2(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Set<String> keys = redisTemplate.keys("Login_*");
        int userId = 999;
        for(String key : keys) {
            boolean isLogin = valueOperations.getBit(key, userId);
            if(isLogin)
                System.out.println(key+" 用户:"+userId+" 登陆");
        }
    }
}
