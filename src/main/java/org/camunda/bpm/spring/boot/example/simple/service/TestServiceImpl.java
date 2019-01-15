package org.camunda.bpm.spring.boot.example.simple.service;

import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl  implements TestService {
    @Override
    public String testRun(String word) {
        return word;
    }
}
