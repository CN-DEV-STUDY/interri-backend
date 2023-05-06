package com.cn.interri.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfiguration {

    @Bean
    public JobDetail quartzJobDetail() {
        return JobBuilder.newJob(BatchScheduledJob.class)
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger jobTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(quartzJobDetail())
                .withSchedule(CronScheduleBuilder.cronSchedule("00 00 00 ? * SUN")) // 매주 일요일 자정(0시 0분 0초)에 실행
                .build();
    }
}
