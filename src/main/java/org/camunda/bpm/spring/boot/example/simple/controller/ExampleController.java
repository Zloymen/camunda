package org.camunda.bpm.spring.boot.example.simple.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.spring.boot.example.simple.Showcase;
import org.camunda.bpm.spring.boot.example.simple.dto.TaskDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/example")
@RequiredArgsConstructor
@Slf4j
public class ExampleController {

    private final RuntimeService runtimeService;

    private final TaskService taskService;

    private final Showcase showcase;

    @PostMapping(value = "/process/{processName}/")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String runProcess(@PathVariable("processName") String processName, @RequestBody Map<String, Object> params){
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processName, params);
        return processInstance == null ? null : processInstance.getProcessInstanceId();
    }

    @GetMapping(value = "/test/")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void runTest(){
        //runtimeService.startProcessInstanceById()
        showcase.runTest();
    }

    @GetMapping(value = "/task/{taskName}/")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getTaskByTaskName(@PathVariable("taskName") String taskName){
        return taskService.createTaskQuery().taskDefinitionKey(taskName).active().list().stream()
                .map(item ->
                        TaskDto.builder().processId(item.getProcessInstanceId()).taskId(item.getId()).build())
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/task/{taskId}/complete")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void compliteTask(@PathVariable("taskId") String taskId, @RequestBody Map<String, Object> params){
        if(params.isEmpty()) taskService.complete(taskId);
            else taskService.complete(taskId, params);
    }

    @GetMapping("/process/in")
    public String run(){
        String uid = UUID.randomUUID().toString();
        //ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Sample", uid, "Task_1kr44wt");

        ProcessInstance processInstance = runtimeService.createProcessInstanceByKey("Sample").startBeforeActivity("Task_1kr44wt").execute();


        return processInstance.getCaseInstanceId();
    }

}
