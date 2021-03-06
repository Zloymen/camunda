package org.camunda.bpm.spring.boot.example.simple.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Slf4j
public class PrintDelegate implements JavaDelegate, PrintVariables {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("print");
        printAll(log, execution.getVariables());
    }
}
