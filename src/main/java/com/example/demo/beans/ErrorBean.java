package com.example.demo.beans;


import com.example.demo.dto.Producto;
import com.example.demo.dto.out.Error;
import com.example.demo.dto.out.ExceptionClass;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ErrorBean {

    /*
    public static void createBadRequestException(Exchange exchange){

        Integer httpCode = 400;
        System.out.println("AQUI LLEGA AL BEAN");
        Producto producto = exchange.getProperty("producto", Producto.class);
        System.out.println("producto del bean= " + producto);
        ExceptionClass exception = new ExceptionClass();
        exception.setCode(400);
        exception.setSuccessful(false);
        List<Error> errors = new ArrayList<>();
        if(producto.getNombre().isEmpty()){
            Error error = new Error();
            error.setCode(422);
            error.setDescription("El nombre del producto es un campo requerido");
            errors.add(error);
        }
        if(producto.getPrecio()<0.0){
            Error error = new Error();
            error.setCode(422);
            error.setDescription("El precio del producto debe ser un numero positivo");
            errors.add(error);
        }

        exception.setErrors(errors);
        System.out.println("hasta aqui tambien melo");
        exchange.setProperty("error", exception);

        exchange.getMessage().setHeader(Exchange.HTTP_RESPONSE_CODE, httpCode);
        exchange.getMessage().setBody(exception);

        Integer response = exchange.getMessage().getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
        System.out.println("response = " + response);
    }
     */
    public static void createBadRequestException(Exchange exchange) {
        Integer httpCode = 400;
        Producto producto = exchange.getProperty("producto", Producto.class);

        ExceptionClass exception = new ExceptionClass();
        exception.setCode(400);
        exception.setSuccessful(false);
        List<Error> errors = new ArrayList<>();

        if (producto.getNombre().isEmpty()) {
            Error error = new Error();
            error.setCode(422);
            error.setDescription("El nombre del producto es un campo requerido");
            errors.add(error);
        }
        if (producto.getPrecio() < 0.0) {
            Error error = new Error();
            error.setCode(422);
            error.setDescription("El precio del producto debe ser un numero positivo");
            errors.add(error);
        }

        exception.setErrors(errors);
/*
        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, httpCode);
        exchange.getIn().setBody(exception);

        Integer response = exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
        System.out.println("response = " + response);

 */
        exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, httpCode);
        exchange.getOut().setBody(exception);
       // exchange.getIn().setBody(exception);

        Integer response = exchange.getOut().getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
        System.out.println("response = " + response);
    }

}
