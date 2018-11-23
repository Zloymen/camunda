package org.camunda.bpm.spring.boot.example.simple;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.camunda.bpm.extension.mockito.DelegateExpressions.autoMock;

/**
 * Ensure the sample.bpmn Process is working correctly.
 */
@Deployment(resources = "bpmn/sample.bpmn")
public class SampleProcessTest extends AbstractProcessEngineRuleTest {

  @Test
  public void start_and_finish_process() {
    autoMock("bpmn/sample.bpmn");

    final ProcessInstance processInstance = runtimeService().startProcessInstanceByKey("Sample");

    assertThat(processInstance).isWaitingAt("UserTask_1");

    complete(task());

    assertThat(processInstance).isWaitingAt("ServiceTask_1");
    execute(job());

    assertThat(processInstance).isEnded();
  }
}
