package org.camunda.bpm.spring.boot.example.simple.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ClientGetterDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {

        Long size = (Long) execution.getVariable("size");
        LocalDate billingDate = getBillingDate(execution.getVariable("billingDate"));

        List<Integer> result = IntStream.rangeClosed(1, 50).boxed().collect(Collectors.toList());

        final AtomicInteger counter = new AtomicInteger();

        Collection<List<Integer>> packages = result.stream()
                .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / size.intValue()))
                .values();

        execution.setVariable("packages", new ArrayList<>(packages));
        execution.setVariable("billingDate", billingDate);
    }

    private LocalDate getBillingDate(Object date){

        if(date instanceof LocalDate) return (LocalDate) date;

        if(date instanceof Date) return new java.sql.Date(((Date)date).getTime()).toLocalDate();

        throw new RuntimeException("Unknown type date");

    }
}
