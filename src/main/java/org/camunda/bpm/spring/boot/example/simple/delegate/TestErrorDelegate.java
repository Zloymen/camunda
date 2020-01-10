package org.camunda.bpm.spring.boot.example.simple.delegate;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class TestErrorDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {

        Object oCheck = execution.getVariable("check");
        boolean manualApprove = oCheck != null ? (Boolean) oCheck : false;
        if (!manualApprove) {
            //execution.createIncident("Failed Job", "error", "все пропало");

            //BpmnError
            //execution.getProcessEngineServices().getRuntimeService().createIncident("Failed Job", execution.getId(), "", "все пропало");
            throw new RuntimeException("все пропало");
            //throw new RuntimeException();
        }
    }
}
