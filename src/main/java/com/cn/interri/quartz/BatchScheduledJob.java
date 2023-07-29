package com.cn.interri.quartz;

import com.cn.interri.batch.job.WeeklyJobConfiguration;
import lombok.RequiredArgsConstructor;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Configuration
@RequiredArgsConstructor
public class BatchScheduledJob extends QuartzJobBean {

    private final WeeklyJobConfiguration WeeklyRankingJobConfiguration;
    private final JobExplorer jobExplorer;
    private final JobLauncher jobLauncher;


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobParameters jobParameters = new JobParametersBuilder(jobExplorer)
                .getNextJobParameters(WeeklyRankingJobConfiguration.weeklyJob(null, null))
                .toJobParameters();

        try {
            jobLauncher.run(WeeklyRankingJobConfiguration.weeklyJob(null, null), jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
