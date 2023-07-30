package com.cn.interri.batch.job;

import com.cn.interri.batch.dto.WeeklyRankingDto;
import com.cn.interri.batch.listener.JobLoggerListener;
import com.cn.interri.batch.listener.StepLoggerListener;
import com.cn.interri.common.dto.RedisKey;
import com.cn.interri.design.inquiry.repository.DesignReqRepository;
import com.cn.interri.batch.dto.InteriorTrendDto;
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
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

/**
 * 주간 랭킹을 계산하는 잡
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class WeeklyJobConfiguration {

    private final UserRepository userRepository;
    private final DesignReqRepository designReqRepository;
    private final RedisTemplate<String, InteriorTrendDto> interiorTrendRedisTemplate;
    private final RedisTemplate<String, WeeklyRankingDto> weeklyRankingRedisTemplate;

    @Bean
    public Job weeklyJob(JobRepository jobRepository, Step weeklyRankingStep) {
        return new JobBuilder("weeklyJob", jobRepository)
                .listener(JobListenerFactoryBean.getListener(new JobLoggerListener()))
//                .incrementer(new WeeklyJobTimeStamper())
                .incrementer(new RunIdIncrementer())
                .start(weeklyRankingStep)
                .build();
    }

    @Bean
    public Step weeklyStep(JobRepository jobRepository, Tasklet weeklyRankingTasklet, PlatformTransactionManager transactionManager) {
        return new StepBuilder("weeklyStep", jobRepository)
                .listener(new StepLoggerListener())
                .tasklet(weeklyRankingTasklet, transactionManager)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Tasklet weeklyTasklet() {
        return (stepContribution, chunkContext) -> {
            List<InteriorTrendDto> weeklyTrends = designReqRepository.getWeekTrends();
            List<WeeklyRankingDto> weeklyRanking = userRepository.getWeeklyRanking();


            // 인테리어 트렌드
            interiorTrendRedisTemplate.delete(RedisKey.INTERIOR_TREND);
            ListOperations<String, InteriorTrendDto> interiorTrendListOperations = interiorTrendRedisTemplate.opsForList();
            interiorTrendListOperations.rightPushAll(RedisKey.INTERIOR_TREND, weeklyTrends);
            interiorTrendRedisTemplate.expireAt(RedisKey.INTERIOR_TREND, Date.from(ZonedDateTime.now().plusDays(7).toInstant())); // 일주일

            // 주간 랭킹
            interiorTrendRedisTemplate.delete(RedisKey.WEEKLY_RANKING);
            ListOperations<String, WeeklyRankingDto> weeklyRankingListOperations = weeklyRankingRedisTemplate.opsForList();
            weeklyRankingListOperations.rightPushAll(RedisKey.WEEKLY_RANKING, weeklyRanking);
            weeklyRankingRedisTemplate.expireAt(RedisKey.WEEKLY_RANKING, Date.from(ZonedDateTime.now().plusDays(7).toInstant())); // 일주일

            return RepeatStatus.FINISHED;

        };
    }
}
