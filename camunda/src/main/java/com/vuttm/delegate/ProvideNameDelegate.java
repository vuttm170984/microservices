package com.vuttm.delegate;

import java.util.Random;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ProvideNameDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Random random = new Random();
        
        execution.setVariable("vuttm", random.nextBoolean());
    }

}
