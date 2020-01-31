package org.camunda.bpm.spring.boot.example.simple.delegate.serialized;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Map;
import java.util.UUID;

@Slf4j
public class DataConversionDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Map<String, Object> data = (Map<String, Object>) execution.getVariable("data");

        String uuid = UUID.randomUUID().toString();

        log.info("uuid: {}", uuid);

        for(Map.Entry<String, Object> entry : data.entrySet()){
            entry.setValue(entry.getValue() + uuid);
        }

    }
}
