package com.cn.interri.batch.controller;

import com.cn.interri.batch.job.WeeklyRankingJobConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/batch")
public class BatchController {

    private final JobLauncher jobLauncher;
    private final WeeklyRankingJobConfiguration weeklyRankingJobConfiguration;


    @GetMapping("/weekly-ranking")
    public ExitStatus runWeeklyRankingJob () throws Exception {
        Job job = weeklyRankingJobConfiguration.weeklyRankingJob(null, null);

        return jobLauncher.run(job, new JobParameters())
                .getExitStatus();
    }
}
