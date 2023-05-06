package com.cn.interri.batch.controller;

import com.cn.interri.batch.job.WeeklyRankingJobConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController
@RequiredArgsConstructor
@RequestMapping("/batch")
public class BatchController {

    private final JobLauncher jobLauncher;
    private final WeeklyRankingJobConfiguration weeklyRankingJobConfiguration;


    @GetMapping("/run/weekly-ranking")
    public ExitStatus runWeeklyRankingJob () throws Exception {
        Job job = weeklyRankingJobConfiguration.weeklyRankingJob();

        return jobLauncher.run(job, new JobParametersBuilder(new Properties()).toJobParameters())
                .getExitStatus();
    }
}
