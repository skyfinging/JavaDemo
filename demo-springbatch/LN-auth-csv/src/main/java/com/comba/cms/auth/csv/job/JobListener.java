package com.comba.cms.auth.csv.job;

import com.comba.cms.auth.csv.config.JobConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Log4j2
public class JobListener implements JobExecutionListener {
    @Autowired
    JobConfig jobConfig;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("启动任务："+jobExecution.getJobParameters());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        String result = getResult(jobExecution);
        if(result.contains("成功")){
            log.info(result+"："+jobExecution.getJobParameters());
            log.info("文件生成:" + jobConfig.getFilePath());
        }else{
            log.warn(result);
        }
    }

    public static String getResult(JobExecution jobExecution){
        BatchStatus status = jobExecution.getStatus();
        switch (status){
            case COMPLETED:
                return "任务成功结束";
            case FAILED:
            case ABANDONED:
                return "任务出错";
            default:
                return "任务状态异常";
        }
    }
}
