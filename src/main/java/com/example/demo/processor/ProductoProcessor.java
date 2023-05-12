package com.example.demo.processor;

import com.example.demo.beans.ErrorBean;
import com.example.demo.dto.Producto;
import com.example.demo.dto.Prueba;
import com.example.demo.dto.out.ExceptionClass;
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
            case "actualizar":
                Integer idProducto = Integer.parseInt(exchange.getIn().getHeader("id_producto", String.class));
                productoService.actualizarProducto(producto,idProducto );
                break;
            case "eliminar":
                Integer idProducto2 = Integer.parseInt(exchange.getIn().getHeader("id_producto", String.class));
                productoService.eliminarProducto(idProducto2);
                exchange.getIn().setBody("Producto eliminado exitosamente");
                break;
            case "consultar":
                Integer idProducto3 = Integer.parseInt(exchange.getIn().getHeader("id_producto", String.class));
                exchange.getIn().setBody(productoService.consultarProducto(idProducto3));
                break;

            case "consultarProductos":
              //  exchange.getIn().setBody(productoService.consultarProducto(idProducto3));
                exchange.getIn().setBody(productoService.consultarProductos());
                break;
        }



    }
}
