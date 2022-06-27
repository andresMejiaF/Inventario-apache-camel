package com.example.resource;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;

public class AppResource extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet")
                .port(9090).host("localhost").bindingMode(RestBindingMode.json);

        rest().get("/service").produces(MediaType.APPLICATION_JSON_VALUE)
                .route().setBody(constant("Welcome to the page"));
    }
}