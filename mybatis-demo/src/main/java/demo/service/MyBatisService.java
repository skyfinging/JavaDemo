package demo.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
@Slf4j
public class MyBatisService {

//    方式1
    @Bean
    public SqlSessionFactory getSqlSessionFactory(){
        try(InputStream is = Resources.getResourceAsStream("mybatis-cfg.xml")) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            return sqlSessionFactory;
        }catch (IOException e){
            log.error(e.getMessage() ,e);
        }
        return null;
    }

//    方式1
//    @Bean
//    public MapperFactoryBean getUserMapper() throws Exception {
//        MapperFactoryBean mapperFactoryBean = new MapperFactoryBean();
//        mapperFactoryBean.setMapperInterface(UserMapper.class);
//        mapperFactoryBean.setSqlSessionFactory(getSqlSessionFactory());
//        return mapperFactoryBean;
//    }

}
