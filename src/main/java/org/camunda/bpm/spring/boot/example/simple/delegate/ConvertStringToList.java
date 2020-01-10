package org.camunda.bpm.spring.boot.example.simple.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ConvertStringToList implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {

        log.info("converter String");

        String idsStr = (String) execution.getVariable("ids_str");

        List<Integer> ids = Arrays.stream(idsStr.split(",")).map(Integer::valueOf).collect(Collectors.toList());

        execution.setVariable("ids", ids);
    }
}
