package org.camunda.bpm.spring.boot.example.simple.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class GetterBillingDataForClient implements JavaDelegate, PrintVariables {

    private static final Random random = new Random();

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        List<Integer> clients = (List<Integer>) execution.getVariable("clients");

        Map<Integer, Object> dataClients = clients.stream().map(GetterBillingDataForClient::generateData)
                .collect(Collectors.toMap(item -> (Integer) item.get("id"), Function.identity()));

        printAll(log, execution.getVariables());

        execution.setVariable("dataClients", dataClients);
        execution.setVariable("resultData", new HashMap<Integer, Object>());
    }

    private static Integer generate(){
        return random.nextInt(100);
    }

    private static Map<String, Object> generateData(Integer id){
        Map<String, Object> result = new HashMap<>();


        result.put("loans", generate());
        result.put("payments", generate());

        result.put("id", id);

        return result;
    }
}
