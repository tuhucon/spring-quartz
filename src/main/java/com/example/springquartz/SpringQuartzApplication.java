package com.example.springquartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.jdbcjobstore.JobStoreCMT;
import org.quartz.impl.jdbcjobstore.JobStoreTX;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.LocalDataSourceJobStore;

import javax.sql.DataSource;

@SpringBootApplication
public class SpringQuartzApplication {

    @Bean
    JobDetail getJob1Detail() {
        return JobBuilder.newJob(Job1Detail.class)
                .storeDurably(true)
                .requestRecovery()
                .withIdentity(Job1Detail.class.getSimpleName())
                .withDescription("Job 1 Detail")
                .build();
    }

    @Bean
    Trigger getJob1DetailTrigger(JobDetail job1Detail) {
        String cronExpression = "0 */1 * ? * *";
        Trigger job1DetailTrigger = TriggerBuilder.<CronTrigger>newTrigger()
                .forJob(job1Detail)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing())
                .withIdentity(Job1Detail.class.getSimpleName() + "Trigger")
                .withDescription("Job 1 Detail Trigger")
                .startNow()
                .build();
        return job1DetailTrigger;
    }

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(SpringQuartzApplication.class, args);
        Thread.sleep(100_000_000L);
    }

}
