package org.camunda.bpm.spring.boot.example.simple.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class GetterBillingDataForClient implements JavaDelegate, PrintVariables {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        List<Integer> clients = (List<Integer>) execution.getVariable("clients");
        LocalDate billingDate = (LocalDate) execution.getVariable("billingDate");

        log.info("billingDate: {}", billingDate);

        Map<Integer, Map<String, Object>> dataClients = clients.stream()
                .collect(Collectors.toMap(item -> item, item -> {
                    Map<String, Object> data = new HashMap<>();
                    data.put("value","клиент №" + item);
                    data.put("clientId",item);
                    return  data;
                }));

        printAll(log, execution.getVariables());

        execution.setVariable("dataClients", dataClients);
        execution.setVariable("resultData", new HashMap<Integer, Object>());
    }

}
