package com.comba.cms.auth.csv.job;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInterruptedException;
import org.springframework.batch.core.StartLimitExceededException;
import org.springframework.batch.core.job.SimpleJob;
import org.springframework.batch.core.repository.JobRestartException;

//线程同步的job
public class SyncSimpleJob extends SimpleJob {

    public SyncSimpleJob(String name) {
        super(name);
    }

    @Override
    protected void doExecute(JobExecution execution) throws JobInterruptedException, JobRestartException,
            StartLimitExceededException {
        synchronized (this){
            super.doExecute(execution);
        }
    }
}
