package com.example.demo.processor;

import com.example.demo.dto.Prueba;
import com.example.demo.resource.PruebaTransactional;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PruebaProcessor implements Processor {




    @Autowired
    private PruebaTransactional pruebaTransactional;


    @Override
    public void process(Exchange exchange) throws Exception {

        Prueba prueba = new Prueba();
                System.out.println("LLega aquii");
                prueba = exchange.getMessage().getBody(Prueba.class);
      //   prueba =  exchange.getIn().getBody(Prueba.class);
        System.out.println("prueba = " + prueba);
       // System.out.println("prueba = " + prueba.toString());
        pruebaTransactional.guardar(prueba);
    }

}
