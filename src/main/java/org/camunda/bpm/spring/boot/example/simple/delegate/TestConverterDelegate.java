package org.camunda.bpm.spring.boot.example.simple.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Comparator;
import java.util.List;

@Slf4j
public class TestConverterDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {

        log.info("converter");
        if(execution.hasVariable("ids")){
            List<Integer> ids = (List<Integer>) execution.getVariable("ids");
            ids.sort(Comparator.reverseOrder());
            execution.setVariable("reverseIds", ids);
        }
        Thread.sleep(2000L);

        throw new RuntimeException("Error Converter");
    }
}
