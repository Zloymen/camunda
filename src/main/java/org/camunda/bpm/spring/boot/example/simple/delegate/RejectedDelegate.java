package org.camunda.bpm.spring.boot.example.simple.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Slf4j
public class RejectedDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("RejectedDelegate run for process {}", execution.getProcessInstanceId());
    }
}
