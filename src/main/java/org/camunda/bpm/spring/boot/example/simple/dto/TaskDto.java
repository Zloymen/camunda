package org.camunda.bpm.spring.boot.example.simple.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskDto {

    private String taskId;

    private String processId;

}
