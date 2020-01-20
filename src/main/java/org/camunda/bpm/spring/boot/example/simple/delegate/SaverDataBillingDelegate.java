package org.camunda.bpm.spring.boot.example.simple.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class SaverDataBillingDelegate implements JavaDelegate, PrintVariables {

    private static final Logger log = LoggerFactory.getLogger(SaverDataBillingDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("save Billing");

        Map<Integer, Object> data = (Map<Integer, Object>) execution.getVariable("dataClients");

        printAll(log, data);




        Thread.sleep(30000L);
    }
}
