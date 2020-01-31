package org.camunda.bpm.spring.boot.example.simple.delegate.serialized;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;
import java.util.Map;

public class GenerateDataDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Map<String, Object> data = new HashMap<>();

        data.put("key1", "key1:");
        data.put("key2", "key2:");
        data.put("key3", "key3:");
        data.put("key4", "key4:");
        data.put("key5", "key5:");

        execution.setVariable("data", data);
    }
}
