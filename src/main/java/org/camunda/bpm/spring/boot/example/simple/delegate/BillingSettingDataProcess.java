package org.camunda.bpm.spring.boot.example.simple.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.List;

public class BillingSettingDataProcess implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        int nPackage = execution.hasVariable("nPackage") ? (Integer) execution.getVariable("nPackage") : 0;

        List<List<Integer>> packages = (List<List<Integer>>) execution.getVariable("packages");

        execution.setVariable("package", packages.get(nPackage));
        execution.setVariable("nPackage", nPackage + 1);
        execution.setVariable("allPackage", packages.size());
    }
}
