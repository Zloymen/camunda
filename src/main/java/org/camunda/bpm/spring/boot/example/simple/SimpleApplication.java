package org.camunda.bpm.spring.boot.example.simple;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.spring.boot.example.simple.delegate.*;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableScheduling
@EnableProcessApplication("mySimpleApplication")
@Slf4j
public class SimpleApplication {

    @Autowired
    private ProcessEngineConfigurationImpl configuration;

    @Bean("rejectPushDelegate")
    RejectPushDelegate createRejectPush() {
        return new RejectPushDelegate();
    }

    @Bean("approveDelegate")
    ApproveDelegate createApproved() {
        return new ApproveDelegate();
    }

    @Bean("rejectedDelegate")
    RejectedDelegate createRejected() {
        return new RejectedDelegate();
    }

    @Bean("autoСheckDelegate")
    AutoСheckDelegate createAutoCheck() {
        return new AutoСheckDelegate();
    }

    @Bean("testErrorDelegate")
    TestErrorDelegate createTestErrorDelegate(){
        return new TestErrorDelegate();
    }


    public static void main(final String... args) {
        SpringApplication.run(SimpleApplication.class, args);
    }

    @PostConstruct
    void init(){
        configuration.setJavaSerializationFormatEnabled(true);
    }


}
