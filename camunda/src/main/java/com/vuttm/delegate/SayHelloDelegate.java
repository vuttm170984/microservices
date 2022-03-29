package com.vuttm.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SayHelloDelegate implements JavaDelegate {

    private final Logger log = LoggerFactory.getLogger(SayHelloDelegate.class);
    
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("Say hello - {}", execution.getCurrentActivityId());
    }

}
