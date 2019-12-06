package org.camunda.bpm.spring.boot.example.simple.service;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("DelegateService")
@Slf4j
public class DelegateService {

    //${execution.setVariable("page", 1)}

    public void execute1(DelegateExecution execution) {
        boolean manualApprove = (Boolean) execution.getVariable("manualApprove");
        boolean autoApprove = (Boolean) execution.getVariable("autoApprove");
        log.info("executed DelegateExecution execute1: {}, manualApprove: {} autoApprove:{}",
                execution, manualApprove, autoApprove);
    }

    public void execute2(DelegateExecution execution) {
        boolean manualApprove = (Boolean) execution.getVariable("manualApprove");
        boolean autoApprove = (Boolean) execution.getVariable("autoApprove");
        log.info("executed DelegateExecution execute2: {}, manualApprove: {} autoApprove:{}",
                execution, manualApprove, autoApprove);
    }

    public void print(DelegateExecution execution){

        Map<String, Object> variables = execution.getVariables();

        variables.keySet().forEach(key -> log.info("key: {}, value: {}", key, variables.get(key)));


    }
}
