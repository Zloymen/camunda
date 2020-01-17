package org.camunda.bpm.spring.boot.example.simple.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


public class StartProcess implements JavaDelegate {
    private static final Logger log = LoggerFactory.getLogger(StartProcess.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Map<String, Object> variables = (Map) execution.getVariable("variables");
        String processName = (String) execution.getVariable("processName");

        ProcessInstance processApplication = execution.getProcessEngineServices().getRuntimeService().startProcessInstanceByKey(processName, variables);
        log.info("Успешно запущен '{}', processInstanceId:{}", processName, processApplication.getProcessInstanceId());
    }
}
