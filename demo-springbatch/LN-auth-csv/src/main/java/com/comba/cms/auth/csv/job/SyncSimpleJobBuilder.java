package com.comba.cms.auth.csv.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.SimpleJob;
import org.springframework.batch.core.job.builder.*;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.core.task.TaskExecutor;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public class SyncSimpleJobBuilder extends SimpleJobBuilder {
    /**
     * Create a new builder initialized with any properties in the parent. The parent is copied, so it can be re-used.
     *
     * @param parent the parent to use
     */
    public SyncSimpleJobBuilder(JobBuilderHelper<?> parent) {
        super(parent);
    }

    private List<Step> steps = new ArrayList<Step>();

    private JobFlowBuilder builder;

    public Job build() {
        if (builder != null) {
            return builder.end().build();
        }
        SimpleJob job = new SyncSimpleJob(getName());
        super.enhance(job);
        job.setSteps(steps);
        try {
            job.afterPropertiesSet();
        }
        catch (Exception e) {
            throw new JobBuilderException(e);
        }
        return job;
    }

    /**
     * Start the job with this step.
     *
     * @param step a step to start with
     * @return this for fluent chaining
     */
    public SimpleJobBuilder start(Step step) {
        if (steps.isEmpty()) {
            steps.add(step);
        }
        else {
            steps.set(0, step);
        }
        return this;
    }

    /**
     * Branch into a flow conditional on the outcome of the current step.
     *
     * @param pattern a pattern for the exit status of the current step
     * @return a builder for fluent chaining
     */
    public FlowBuilder.TransitionBuilder<FlowJobBuilder> on(String pattern) {
        Assert.state(steps.size() > 0, "You have to start a job with a step");
        for (Step step : steps) {
            if (builder == null) {
                builder = new JobFlowBuilder(new FlowJobBuilder(this), step);
            }
            else {
                builder.next(step);
            }
        }
        return builder.on(pattern);
    }

    /**
     * Start with this decider. Returns a flow builder and when the flow is ended a job builder will be returned to
     * continue the job configuration if needed.
     *
     * @param decider a decider to execute first
     * @return builder for fluent chaining
     */
    public JobFlowBuilder start(JobExecutionDecider decider) {
        if (builder == null) {
            builder = new JobFlowBuilder(new FlowJobBuilder(this), decider);
        }
        else {
            builder.start(decider);
        }
        if (!steps.isEmpty()) {
            steps.remove(0);
        }
        for (Step step : steps) {
            builder.next(step);
        }
        return builder;
    }

    /**
     * Continue with this decider if the previous step was successful. Returns a flow builder and when the flow is ended
     * a job builder will be returned to continue the job configuration if needed.
     *
     * @param decider a decider to execute next
     * @return builder for fluent chaining
     */
    public JobFlowBuilder next(JobExecutionDecider decider) {
        for (Step step : steps) {
            if (builder == null) {
                builder = new JobFlowBuilder(new FlowJobBuilder(this), step);
            }
            else {
                builder.next(step);
            }
        }
        if (builder == null) {
            builder = new JobFlowBuilder(new FlowJobBuilder(this), decider);
        }
        else {
            builder.next(decider);
        }
        return builder;
    }

    /**
     * Continue or end a job with this step if the previous step was successful.
     *
     * @param step a step to execute next
     * @return this for fluent chaining
     */
    public SimpleJobBuilder next(Step step) {
        steps.add(step);
        return this;
    }

    /**
     * @param executor instance of {@link TaskExecutor} to be used.
     * @return builder for fluent chaining
     */
    public JobFlowBuilder.SplitBuilder<FlowJobBuilder> split(TaskExecutor executor) {
        for (Step step : steps) {
            if (builder == null) {
                builder = new JobFlowBuilder(new FlowJobBuilder(this), step);
            }
            else {
                builder.next(step);
            }
        }
        if (builder == null) {
            builder = new JobFlowBuilder(new FlowJobBuilder(this));
        }
        return builder.split(executor);
    }
}
