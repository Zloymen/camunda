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

        Map<Integer, Map<String, Object>> dataClients = (Map<Integer, Map<String, Object>>) execution.getVariable("dataClients");
        Map<Integer, Object> resultData = (Map<Integer, Object>) execution.getVariable("resultData");

        printAll(log, resultData);
        for(Map.Entry<Integer, Map<String, Object>> client : dataClients.entrySet()){
            Map<String, Object> data = client.getValue();
            log.info("value client : {} = {}", client.getKey(), data.get("value"));
        }
        //Thread.sleep(30000L);
    }
}
