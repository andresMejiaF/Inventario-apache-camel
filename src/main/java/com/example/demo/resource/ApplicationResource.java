package com.example.demo.resource;

import com.example.demo.dto.OrderDto;
import com.example.demo.processor.OrderProcessor;
import com.example.demo.service.OrderService;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class ApplicationResource extends RouteBuilder {

    @Autowired
    private OrderService service;

    @Autowired //@BeanInject
    private OrderProcessor processor;

    @Override
    public void configure() throws Exception {
        // build a camel component
        restConfiguration().component("servlet")
                .host("localhost").port(9090).bindingMode(RestBindingMode.json);

        // expose rest api
        rest().get("/service").produces(MediaType.APPLICATION_JSON_VALUE)
                .route().setBody(constant("Welcome to this page")).endRest();

        rest().get("/getOrders").produces(MediaType.APPLICATION_JSON_VALUE)
                .route().setBody(() -> service.getOrders()).endRest();

        rest().post("/addOrder").consumes(MediaType.APPLICATION_JSON_VALUE)
                .type(OrderDto.class)
                .outType(OrderDto.class)
                .route()
                .process(processor)
                .endRest();
    }
}
