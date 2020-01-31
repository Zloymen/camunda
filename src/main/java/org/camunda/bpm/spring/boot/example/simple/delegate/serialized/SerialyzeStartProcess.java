package org.camunda.bpm.spring.boot.example.simple.delegate.serialized;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
import org.camunda.bpm.engine.runtime.ProcessInstantiationBuilder;

import java.util.Map;

public class SerialyzeStartProcess implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {

        //Map<String, Object> data = new HashMap<>();

        Map<String, Object> data = (Map<String, Object>) execution.getVariable("data");

        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();

        ProcessInstantiationBuilder builder = runtimeService.createProcessInstanceByKey("serialyze-process");
        builder.setVariable("data", data);
        //builder.setVariable("payments", param.getPayments());

        //Map<String, Object> target = (Map<String, Object>) execution.getVariable("dataClient");

        //builder.setVariable("dataClient", target);
        //builder.setVariable("createDate", param.getCreateDate());
        //builder.setVariable("billingDate", recalcDay);

        ProcessInstanceWithVariables instanceWithVariables = builder.executeWithVariablesInReturn();

        //instanceWithVariables.getVariables();
    }
}
