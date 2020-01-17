package org.camunda.bpm.spring.boot.example.simple.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ClientGetterDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {

        Long size = (Long) execution.getVariable("size");

        List<Integer> result = Arrays.asList(1,2,3,4,5,6,7,8,9,10, 11,12,13,14, 15,16,17, 18,19,20,21, 22, 23);

        final AtomicInteger counter = new AtomicInteger();

        Collection<List<Integer>> packages = result.stream()
                .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / size.intValue()))
                .values();

        execution.setVariable("packages", new ArrayList<>(packages));
    }
}
