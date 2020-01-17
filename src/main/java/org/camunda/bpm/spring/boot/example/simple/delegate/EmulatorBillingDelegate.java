package org.camunda.bpm.spring.boot.example.simple.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Date;
import java.util.Formatter;
import java.util.Map;

public class EmulatorBillingDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Map<Integer, Object> data = (Map<Integer, Object>) execution.getVariable("data");
        Integer id = (Integer) execution.getVariable("id");
        Date billingDate = (Date) execution.getVariable("billingDate");

        StringBuilder builder = new StringBuilder();


        try(Formatter fmt = new Formatter(builder)){
            fmt.format("id = %d,  billingDate = %s result = %s", id, billingDate.toString(), "расчет совершен");
            data.put(id, builder.toString());
        }

        execution.setVariable("data", data);
    }
}
