package org.camunda.bpm.spring.boot.example.simple;

import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.camunda.bpm.extension.mockito.DelegateExpressions.autoMock;
import static org.mockito.Mockito.mock;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@Deployment(resources = "bpmn/sample.bpmn")
public class ShowcaseTest extends AbstractProcessEngineRuleTest {

  private Showcase showcase;

  @Before
  public void setUp() {
    autoMock("bpmn/sample.bpmn");

    showcase = new Showcase();
    setField(showcase, "runtimeService", runtimeService());
    setField(showcase, "taskService", taskService());
  }

  @Test
  public void startAndFinishProcess() {
    showcase.notify(mock(PostDeployEvent.class));
    final String processInstanceId = showcase.getProcessInstanceId();

    final Job job = jobQuery().active().processInstanceId(processInstanceId).singleResult();

    execute(job);

    assertThat(historyService().createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult().getEndTime()).isNotNull();
  }

}
