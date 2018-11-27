package org.camunda.bpm.spring.boot.example.simple;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.spring.boot.example.simple.delegate.ApproveDelegate;
import org.camunda.bpm.spring.boot.example.simple.delegate.AutoСheckDelegate;
import org.camunda.bpm.spring.boot.example.simple.delegate.RejectPushDelegate;
import org.camunda.bpm.spring.boot.example.simple.delegate.RejectedDelegate;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableProcessApplication("mySimpleApplication")
@Slf4j
public class SimpleApplication {

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


    public static void main(final String... args) {
        SpringApplication.run(SimpleApplication.class, args);
    }


}
