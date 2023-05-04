package com.example.springquartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.transaction.annotation.Transactional;

public class Job1Detail extends QuartzJobBean {

    @Override
    @Transactional(readOnly = true) //set readOnly = true will require connection eagerly without any query
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("job 1 detail with context: " + context);
        try {
            Thread.sleep(30_000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("job 1 detail done");
    }
}
