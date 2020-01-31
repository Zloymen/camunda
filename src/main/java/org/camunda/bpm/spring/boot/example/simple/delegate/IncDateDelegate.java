package org.camunda.bpm.spring.boot.example.simple.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.time.LocalDate;

@Slf4j
public class IncDateDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LocalDate date = (LocalDate) execution.getVariable("startRecalculateDate");

        log.info("startRecalculateDate: {}", date);

        execution.setVariable("startRecalculateDate", date.plusDays(1));
    }
}
