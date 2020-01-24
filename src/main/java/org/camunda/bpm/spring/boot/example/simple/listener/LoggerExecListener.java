package org.camunda.bpm.spring.boot.example.simple.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;

public class LoggerExecListener implements ExecutionListener {

    private static final String MARK = "time_start";
    private static Logger log = LoggerFactory.getLogger("EXEC_TIME_CAMUNDA_LOGGER");

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        switch(execution.getEventName()){
            case ExecutionListener.EVENTNAME_END:
                LocalDateTime begin = (LocalDateTime)execution.getVariable(MARK);

                log.info("instance definition key: {} - id: {} - business key: {} - time exec(sec): {}", execution.getProcessDefinitionId(), execution.getProcessInstanceId(), execution.getProcessBusinessKey(), Duration.between(begin, LocalDateTime.now()).getSeconds());

                return;
            case ExecutionListener.EVENTNAME_START:
                execution.setVariable(MARK, LocalDateTime.now());
                return;
            default:
        }
    }
}