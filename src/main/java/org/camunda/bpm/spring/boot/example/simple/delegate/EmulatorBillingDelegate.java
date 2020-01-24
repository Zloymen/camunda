package org.camunda.bpm.spring.boot.example.simple.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.time.LocalDate;
import java.util.Formatter;
import java.util.Map;

public class EmulatorBillingDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Map<String, Object> data = (Map<String, Object>) execution.getVariable("dataClient");
        Map<Integer, Object> resultData = (Map<Integer, Object>) execution.getVariable("resultData");
        Integer id = (Integer) execution.getVariable("id");
        LocalDate billingDate = (LocalDate) execution.getVariable("billingDate");

        StringBuilder builder = new StringBuilder();

        String value = (String) data.get("value");

        try(Formatter fmt = new Formatter(builder)){
            fmt.format("%s расчет на дату %s совершен", value, billingDate.toString());
            String result = builder.toString();
            resultData.put(id, result);
            data.put("value", result);
        }

        //Thread.sleep(3000);
        execution.setVariable("resultData", resultData);
    }
}
