package com.comba.cms.auth.csv.config;

import com.comba.cms.auth.csv.dto.JurisdictionDto;
import com.comba.cms.auth.csv.job.HeaderWriter;
import com.comba.cms.auth.csv.job.JobListener;
import com.comba.cms.auth.csv.job.SyncJobBuilderFactory;
import lombok.Getter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class JobConfig {

    @Value("${task.sql}")
    private String taskSql;

    @Value("${appid}")
    private String appid;

    @Getter
    @Value("${csv.file.path}")
    private String filePath;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobListener jobListener;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private HeaderWriter headerWriter;

    @Bean
    public SyncJobBuilderFactory syncJobBuilderFactory(){
        //使用多线程同步的job，避免同时启动两个job导致失败
        return new SyncJobBuilderFactory(jobRepository);
    }

    @Bean
    public Job dataHandleJob(SyncJobBuilderFactory syncJobBuilderFactory){
        return syncJobBuilderFactory.get("jurisdiction.csv文件生成任务")
                .incrementer(new RunIdIncrementer())
                .start(handleDataStep())
                .listener(jobListener)
                .build();
    }

    @Bean
    public Step handleDataStep(){
        return stepBuilderFactory
                .get("生成文件")
                .<JurisdictionDto, JurisdictionDto>chunk(100)
                .faultTolerant().retryLimit(3).retry(Exception.class).skipLimit(100).skip(Exception.class)
                .reader(getDataReader())
                .processor(getDataProcessor())
                .writer(getDataWriter())
                .build();
    }

    @Bean
    public ItemReader<JurisdictionDto> getDataReader(){
        JdbcCursorItemReader<JurisdictionDto> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);
        reader.setSql(taskSql);
        reader.setRowMapper(new BeanPropertyRowMapper<>(JurisdictionDto.class));
        reader.setFetchSize(100);
        return reader;
    }

    @Bean
    public ItemProcessor<JurisdictionDto, JurisdictionDto> getDataProcessor(){
        return item -> {
            item.setAppid(appid);
            return item;
        };
    }

    @Bean
    public ItemWriter<JurisdictionDto> getDataWriter(){
        FlatFileItemWriter<JurisdictionDto> itemWriter = new FlatFileItemWriter<>();
        Resource outputResource = new FileSystemResource(filePath);
        itemWriter.setResource(outputResource);
        BeanWrapperFieldExtractor<JurisdictionDto> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(headerWriter.getHeaders());
        fieldExtractor.afterPropertiesSet();
        itemWriter.setLineSeparator("\n");
        itemWriter.setHeaderCallback(headerWriter);
        itemWriter.setLineAggregator(new DelimitedLineAggregator<JurisdictionDto>() {{
            setDelimiter(headerWriter.getCsvSplit());
            setFieldExtractor(fieldExtractor);
        }});
        return itemWriter;
    }

    @Bean
    public ThreadPoolTaskExecutor threadPoolExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(5);
        threadPoolTaskExecutor.setMaxPoolSize(10);
        threadPoolTaskExecutor.setQueueCapacity(10);
        threadPoolTaskExecutor.setThreadNamePrefix("LiaoNing_Auth_CSV_Job");
        return threadPoolTaskExecutor;
    }
}
