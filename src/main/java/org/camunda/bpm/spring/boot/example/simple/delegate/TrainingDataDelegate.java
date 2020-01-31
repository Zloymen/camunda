package org.camunda.bpm.spring.boot.example.simple.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

public class TrainingDataDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {

        execution.setVariable("startRecalculateDate", getBillingDate(execution.getVariable("startRecalculateDate")));

        execution.setVariable("endRecalculationDate", getBillingDate(execution.getVariable("endRecalculationDate")));

        execution.setVariable("clients", Arrays.asList(1,2));
    }

    private LocalDate getBillingDate(Object date){

        if(date instanceof LocalDate) return (LocalDate) date;

        if(date instanceof Date) return new java.sql.Date(((Date)date).getTime()).toLocalDate();

        throw new RuntimeException("Unknown type date");

    }
}
