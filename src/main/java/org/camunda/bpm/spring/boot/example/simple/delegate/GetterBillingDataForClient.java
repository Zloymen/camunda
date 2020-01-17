package org.camunda.bpm.spring.boot.example.simple.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class GetterBillingDataForClient implements JavaDelegate, PrintVariables {

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        printAll(log, execution.getVariables());
        Map<Integer, Object> dataClients = new HashMap<>();
        execution.setVariable("dataClients", dataClients);
    }
}
