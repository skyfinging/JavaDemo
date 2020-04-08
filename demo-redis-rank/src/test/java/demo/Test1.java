package demo;

import demo.config.MyConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@SpringBootTest(classes = Main.class)
@RunWith(SpringRunner.class)
public class Test1 {

    @Autowired
    MyConfig myConfig;

    @Test
    public void delete(){
        myConfig.getRedisTemplate().delete(myConfig.SCORE_RANK);
        System.out.println("删除缓存");
    }

    @Test
    public void batchAdd() {
        //插入10万条数据
        boolean exists = myConfig.getRedisTemplate().hasKey(myConfig.SCORE_RANK);
        if(!exists) {
            Set<ZSetOperations.TypedTuple<String>> tuples = new HashSet<>();
            long start = System.currentTimeMillis();

            for (int i = 0; i < 100000; i++) {
                DefaultTypedTuple<String> tuple = new DefaultTypedTuple<>("张三" + i, 1D + i);
                tuples.add(tuple);
            }
            System.out.println("循环时间:" + (System.currentTimeMillis() - start));
            Long num = myConfig.getRedisTemplate().opsForZSet().add(myConfig.SCORE_RANK, tuples);
            System.out.println("批量新增时间:" + (System.currentTimeMillis() - start));
            System.out.println("受影响行数：" + num);
        }
    }

    /**
     * 统计两个分数之间的人数
     */
    @Test
    public void count(){
        Long count = myConfig.getRedisTemplate().opsForZSet().count(myConfig.SCORE_RANK, 8001, 9000);
        System.out.println("统计8001-9000之间的人数:" + count);
    }

    /**
     * 新插入一条数据
     */
    @Test
    public void add(){
        String name = "李四";
        Double score = Double.valueOf(new Random().nextInt(10000));
        myConfig.getRedisTemplate().opsForZSet().add(myConfig.SCORE_RANK, name, score);
        System.out.println(name+"的分数：" + score);

        Long rank = myConfig.getRedisTemplate().opsForZSet().rank(myConfig.SCORE_RANK, name);
        System.out.println(name+" 的排名是："+rank);
    }

    /**
     * 获取整个集合的基数(数量大小)
     */
    @Test
    public void zCard(){
        Long aLong = myConfig.getRedisTemplate().opsForZSet().zCard(myConfig.SCORE_RANK);
        System.out.println("集合的基数为：" + aLong);
    }

    @Test
    public void rank(){
        String name = "张三999888";
        Long rank = myConfig.getRedisTemplate().opsForZSet().rank(myConfig.SCORE_RANK, name);
        System.out.println(name+" 的排名是："+rank);
    }
}
