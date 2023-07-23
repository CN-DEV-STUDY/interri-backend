package com.cn.interri.batch.job;

import com.cn.interri.batch.listener.JobLoggerListener;
import com.cn.interri.batch.listener.StepLoggerListener;
import com.cn.interri.user.entity.User.User;
import com.cn.interri.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobListenerFactoryBean;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

/**
 * 주간 랭킹을 계산하는 잡
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class WeeklyRankingJobConfiguration {

//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBUilderFactory;
    private final UserRepository userRepository;

    @Bean
    public Job weeklyRankingJob(JobRepository jobRepository, Step weeklyRankingStep) {
        return new JobBuilder("weeklyRankingJob", jobRepository)
                .listener(JobListenerFactoryBean.getListener(new JobLoggerListener()))
//                .incrementer(new WeeklyJobTimeStamper())
                .incrementer(new RunIdIncrementer())
                .start(weeklyRankingStep)
                .build();
    }

    @Bean
    public Step weeklyRankingStep(JobRepository jobRepository, Tasklet weeklyRankingTasklet, PlatformTransactionManager transactionManager) {
        return new StepBuilder("weeklyRandingStep", jobRepository)
                .listener(new StepLoggerListener())
                .tasklet(weeklyRankingTasklet, transactionManager)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Tasklet weeklyRankingTasklet() {
        return (stepContribution, chunkContext) -> {
            log.info("@@@@@@@@@@@@@@@@@@@ BATCH @@@@@@@@@@@@@@@@@@@");
            List<User> users = userRepository.findAll();
            for (User user : users) {
                log.info(user.getUsername() + user.getEmail());
            }
            return RepeatStatus.FINISHED;
        };
    }
}