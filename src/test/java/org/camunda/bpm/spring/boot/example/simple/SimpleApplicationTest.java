package org.camunda.bpm.spring.boot.example.simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SimpleApplication.class }, properties = {
    "org.camunda.bpm.spring.boot.starter.example.simple.SimpleApplication.exitWhenFinished=false" })
public class SimpleApplicationTest {

  @Test
  public void would_fail_if_process_not_completed_after_5_seconds() {

  }
}
