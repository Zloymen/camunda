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
        boolean manualApprove = execution.hasVariable("manualApprove") ? (Boolean) execution.getVariable("manualApprove") : false;
        boolean autoApprove = execution.hasVariable("autoApprove") ? (Boolean) execution.getVariable("autoApprove") : false;
        log.info("executed DelegateExecution execute1: {}, manualApprove: {} autoApprove:{}",
                execution, manualApprove, autoApprove);
    }

    public void execute2(DelegateExecution execution) {
        boolean manualApprove = execution.hasVariable("manualApprove") ? (Boolean) execution.getVariable("manualApprove") : false;
        boolean autoApprove = execution.hasVariable("autoApprove") ? (Boolean) execution.getVariable("autoApprove") : false;
        log.info("executed DelegateExecution execute2: {}, manualApprove: {} autoApprove:{}",
                execution, manualApprove, autoApprove);
    }

    public void print(DelegateExecution execution){

        Map<String, Object> variables = execution.getVariables();

        variables.keySet().forEach(key -> log.info("key: {}, value: {}", key, variables.get(key)));


    }

    public void testError(DelegateExecution execution){

        Object oCheck = execution.getVariable("check");
        boolean manualApprove = oCheck != null ? (Boolean) oCheck : false;
        if (!manualApprove) execution.createIncident("customError", "error", "все пропало");
    }
}
