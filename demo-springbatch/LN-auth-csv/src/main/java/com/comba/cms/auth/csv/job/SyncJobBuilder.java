package com.comba.cms.auth.csv.job;


import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;

public class SyncJobBuilder extends JobBuilder {
    /**
     * Create a new builder for a job with the given name.
     *
     * @param name the name of the job
     */
    public SyncJobBuilder(String name) {
        super(name);
    }

    @Override
    public SimpleJobBuilder start(Step step) {
        return new SyncSimpleJobBuilder(this).start(step);
    }
}
