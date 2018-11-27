package org.camunda.bpm.spring.boot.example.simple.listener;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

@Slf4j
public class EndProcessListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution execution) throws Exception {
        log.info("end start process {}", execution.getProcessInstanceId());
    }
}
