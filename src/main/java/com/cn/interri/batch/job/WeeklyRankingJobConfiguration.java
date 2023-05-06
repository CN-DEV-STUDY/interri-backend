package com.cn.interri.batch.job;

import com.cn.interri.batch.incrementer.WeeklyJobTimeStamper;
import com.cn.interri.batch.listener.JobLoggerListener;
import com.cn.interri.batch.listener.StepLoggerListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobListenerFactoryBean;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 주간 랭킹을 계산하는 잡
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class WeeklyRankingJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBUilderFactory;

    @Bean
    public Job weeklyRankingJob() {
        return jobBuilderFactory.get("weeklyRankingJob")
                .listener(JobListenerFactoryBean.getListener(new JobLoggerListener()))
//                .incrementer(new WeeklyJobTimeStamper())
                .incrementer(new RunIdIncrementer())
                .start(weeklyRankingStep())
                .build();
    }

    @Bean
    public Step weeklyRankingStep() {
        return stepBUilderFactory.get("weeklyRandingStep")
                .listener(new StepLoggerListener())
                .tasklet(weeklyRankingTasklet())
                .build();
    }

    @Bean
    public Tasklet weeklyRankingTasklet() {
        return (stepContribution, chunkContext) -> {



            return RepeatStatus.FINISHED;
        };
    }
}
