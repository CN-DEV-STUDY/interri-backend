package com.cn.interri.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;

@Slf4j
public class StepLoggerListener {

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        log.info("############### STEP_START ###############");
        log.info("{} has begun!", stepExecution.getStepName());
    }

    @AfterStep
    public void afterStep(StepExecution stepExecution) {
        log.info("{} has ended!", stepExecution.getStepName());
        log.info("############### STEP_END ###############");
    }
}
