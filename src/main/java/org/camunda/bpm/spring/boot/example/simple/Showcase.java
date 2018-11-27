package org.camunda.bpm.spring.boot.example.simple;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class Showcase {

    private final RuntimeService runtimeService;

    private final TaskService taskService;

    public void runTest() {

        //запускаем
        String processInstanceId = runtimeService.startProcessInstanceByKey("Sample").getProcessInstanceId();

        log.info("started instance: {}", processInstanceId);

        //поиск задачи по ид процесса
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();

        //потдтвеждение задачи
        taskService.complete(task.getId());

        log.info("completed task: {}", task);

    }

}
