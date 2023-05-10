package com.example.demo.processor;

import com.example.demo.dto.Producto;
import com.example.demo.dto.Prueba;
import com.example.demo.resource.PruebaTransactional;
import com.example.demo.service.ProductoService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductoProcessor implements Processor {


    @Autowired
    private ProductoService productoService;

    @Override
    public void process(Exchange exchange) throws Exception {

        String accion = exchange.getProperty("accion", String.class);
        System.out.println("accion = " + accion);
        Producto producto = new Producto();
        producto = exchange.getMessage().getBody(Producto.class);
        System.out.println("producto = " + producto);
        
        switch (accion) {
            case "agregar":
                    productoService.guardarProducto(producto);
                break;
        }



    }
}
