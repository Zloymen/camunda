package org.camunda.bpm.spring.boot.example.simple.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class RejectPushDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {

    }
}
