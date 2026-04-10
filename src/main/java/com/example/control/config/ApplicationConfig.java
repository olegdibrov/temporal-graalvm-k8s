package com.example.control.config;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Value("${temporal.target}")
    private String target;


    @Bean
    public WorkflowServiceStubs workflowServiceStubs() {
        var workflowServiceStubsOptions =
                WorkflowServiceStubsOptions.newBuilder()
                        .setTarget(target)
                        .build();
        return WorkflowServiceStubs.newServiceStubs(workflowServiceStubsOptions);
    }


    @Bean
    public WorkflowClient workflowClient() {
        return WorkflowClient.newInstance(workflowServiceStubs());
    }
}