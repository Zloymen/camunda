package org.camunda.bpm.spring.boot.example.simple;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class Showcase {

    private final Logger logger = getLogger(this.getClass());

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private TaskService taskService;

    private String processInstanceId;

    @EventListener
    public void notify(final PostDeployEvent unused) {


        processInstanceId = runtimeService.startProcessInstanceByKey("Sample").getProcessInstanceId();

        runtimeService.startProcessInstanceByKey("Sample");
        runtimeService.startProcessInstanceByKey("Sample");

        logger.info("started instance: {}", processInstanceId);

        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        taskService.complete(task.getId());

        List<Task> tasks = taskService.createTaskQuery().taskDefinitionKey("UserTask_1").active().list();

        task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        if (task != null) taskService.complete(task.getId());

        logger.info("completed task: {}", task);

        // now jobExecutor should execute the async job
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }
}
