package org.camunda.bpm.spring.boot.example.simple;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SayHelloDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        boolean manualApprove = (Boolean) execution.getVariable("manualApprove");
        boolean autoApprove = (Boolean) execution.getVariable("autoApprove");
        log.info("executed sayHelloDelegate: {}, manualApprove: {} autoApprove:{}",
                execution, manualApprove, autoApprove);
    }

}
