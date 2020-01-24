package org.camunda.bpm.spring.boot.example.simple.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateDataDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {

        Map<String, List<String>> data = new HashMap<>();

        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("tree");

        data.put("list", list);

        execution.setVariable("data", data);
    }
}
