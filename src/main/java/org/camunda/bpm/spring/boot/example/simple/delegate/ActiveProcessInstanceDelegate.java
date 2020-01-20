package org.camunda.bpm.spring.boot.example.simple.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Slf4j
public class ActiveProcessInstanceDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {

        String definitionKey = (String) execution.getVariable("definitionKey");

        long count = execution.getProcessEngineServices().getRuntimeService().createProcessInstanceQuery()
                .processDefinitionKey(definitionKey)
                .count();

        log.info("count for definitionKey '{}' :{}", definitionKey, count);

        execution.setVariable("countActiveProcess", (int) count);
    }
}
