package org.camunda.bpm.spring.boot.example.simple.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Date;
import java.util.Formatter;
import java.util.Map;

public class EmulatorBillingDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Map<String, Object> data = (Map<String, Object>) execution.getVariable("dataClient");
        Map<Integer, Object> resultData = (Map<Integer, Object>) execution.getVariable("resultData");
        Integer id = (Integer) execution.getVariable("id");
        Date billingDate = (Date) execution.getVariable("billingDate");

        StringBuilder builder = new StringBuilder();


        try(Formatter fmt = new Formatter(builder)){
            fmt.format("id = %d,  billingDate = %s result = %s", id, billingDate.toString(), "расчет совершен");
            resultData.put(id, builder.toString());
        }

        //Thread.sleep(3000);
        execution.setVariable("resultData", resultData);
    }
}
