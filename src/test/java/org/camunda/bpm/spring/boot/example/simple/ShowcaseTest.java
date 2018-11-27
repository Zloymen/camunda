package org.camunda.bpm.spring.boot.example.simple;

import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.junit.Before;
import org.junit.Test;

import static org.camunda.bpm.extension.mockito.DelegateExpressions.autoMock;

@Deployment(resources = "bpmn/sample.bpmn")
public class ShowcaseTest extends AbstractProcessEngineRuleTest {

  private Showcase showcase;

  @Before
  public void setUp() {
    autoMock("bpmn/sample.bpmn");


  }

  @Test
  public void startAndFinishProcess() {

  }

}
