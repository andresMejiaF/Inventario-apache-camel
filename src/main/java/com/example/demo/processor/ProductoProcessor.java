package com.example.demo.processor;

import com.example.demo.beans.ErrorBean;
import com.example.demo.dto.Producto;
import com.example.demo.dto.Prueba;
import com.example.demo.dto.out.ExceptionClass;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.BadRequestExceptionId;
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

                if(producto.getNombre().isEmpty() || producto.getPrecio()<0) {
                    throw new BadRequestException("error en procesamiento");
                }else{
                    productoService.guardarProducto(producto);
                }

                break;
            case "actualizar":
                Integer idProducto = Integer.parseInt(exchange.getIn().getHeader("id_producto", String.class));
                Producto producto1=  productoService.actualizarProducto(producto,idProducto );
                if(producto1==null){
                    throw new BadRequestExceptionId("error en procesamiento");
                }
                break;
            case "eliminar":
                Integer idProducto2 = Integer.parseInt(exchange.getIn().getHeader("id_producto", String.class));
                Producto producto2= productoService.eliminarProducto(idProducto2);
                if(producto2==null){
                    throw new BadRequestExceptionId("error en procesamiento");
                }
                exchange.getIn().setBody("Producto eliminado exitosamente");
                break;
            case "consultar":
                Integer idProducto3 = Integer.parseInt(exchange.getIn().getHeader("id_producto", String.class));

                Producto producto3 = productoService.consultarProducto(idProducto3);
                if(producto3==null){
                    throw new BadRequestExceptionId("error en procesamiento");
                }else{
                    exchange.getIn().setBody(producto3);
                }
                break;

            case "consultarProductos":
              //  exchange.getIn().setBody(productoService.consultarProducto(idProducto3));
                exchange.getIn().setBody(productoService.consultarProductos());
                break;
        }



    }
}
