package com.example.control.temporal;

import io.temporal.api.workflowservice.v1.DescribeNamespaceResponse;
import io.temporal.api.workflowservice.v1.ListNamespacesRequest;
import io.temporal.client.WorkflowClient;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TemporalService {

    private final WorkflowClient workflowClient;


    @PostConstruct
    public void init() {
        log.info("Getting namespaces");

        List<DescribeNamespaceResponse> namespaces = workflowClient
                .getWorkflowServiceStubs()
                .blockingStub()
                .listNamespaces(ListNamespacesRequest.newBuilder().build())
                .getNamespacesList();
        log.info("Found {} namespaces", namespaces.size());
    }
}