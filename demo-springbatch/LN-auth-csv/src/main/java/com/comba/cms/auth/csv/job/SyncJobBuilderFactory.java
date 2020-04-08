package com.comba.cms.auth.csv.job;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;

public class SyncJobBuilderFactory extends JobBuilderFactory {
    JobRepository jobRepository ;
    public SyncJobBuilderFactory(JobRepository jobRepository) {
        super(jobRepository);
        this.jobRepository = jobRepository;
    }

    @Override
    public JobBuilder get(String name) {
        SyncJobBuilder builder = new SyncJobBuilder(name);
        builder = (SyncJobBuilder) builder.repository(jobRepository);
        return builder;
    }
}
