package com.cn.interri.batch.incrementer;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

/**
 * 잡 실행 시 현재 주를 파라미터로 받는다.
 */
public class WeeklyJobTimeStamper implements JobParametersIncrementer {

    @Override
    public JobParameters getNext(JobParameters jobParameters) {
        LocalDate localDate = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int weekNumber = localDate.get(weekFields.weekOfWeekBasedYear());

        return new JobParametersBuilder()
                .addLong("currentWeek", Long.valueOf(weekNumber))
                .toJobParameters();
    }
}
