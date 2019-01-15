package org.camunda.bpm.spring.boot.example.simple.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.spring.boot.example.simple.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TrainingInfoDelegate implements JavaDelegate {

    @Autowired
    private TestService testService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info(testService.testRun("TrainingInfoDelegate === test"));
        log.info("TrainingInfoDelegate run for process {}", execution.getProcessInstanceId());
    }

}
