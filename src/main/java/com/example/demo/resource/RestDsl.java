package com.example.demo.resource;

import com.example.demo.beans.ErrorBean;
import com.example.demo.dto.Producto;
import com.example.demo.dto.Prueba;
import com.example.demo.dto.WeatherDto;
import com.example.demo.dto.out.ExceptionClass;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.BadRequestExceptionId;
import com.example.demo.exceptions.ExceptionCampos;
import com.example.demo.processor.*;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class RestDsl extends RouteBuilder {
    @Autowired
    private WeatherDataProvider weatherDataProvider;

    @Autowired
    private SaveWeatherAndSetToExchangeProcessor saveWeatherAndSetToExchangeProcessor;

    @Autowired
    private GetWeatherProcessor getWeatherProcessor;

    @Autowired
    private GetAllWeatherDataProcessor getAllWeatherDataProcessor;

    @Autowired
    private DeleteWeatherProcessor deleteWeatherProcessor;

    @Autowired
    private UpdateWeatherProcessor updateWeatherProcessor;

    @Autowired
    private PruebaProcessor pruebaProcessor;

    @Autowired
    private ProductoProcessor productoProcessor;

    @Autowired
    private ErrorBean errorBean;

    @Override
    public void configure() throws Exception {


        onException(BadRequestException.class)
                .log("se produjo una excepcion en el procesamiento: ${exception.message}")
                .handled(true)
                .bean(errorBean, "createBadRequestException(*)").id("errorBeanException")
                .marshal().json(JsonLibrary.Jackson)
                .end();




        onException(BadRequestExceptionId.class)
                .handled(true)
                .bean(errorBean, "createBadRequestExceptionProductNoexist(*)").id("errorBeanException")
                .marshal().json(JsonLibrary.Jackson)
                .end();

        onException(RuntimeException.class)
                .handled(true)
                .bean(errorBean, "createInternalErrorException(*)").id("errorBeanException")
                .marshal().json(JsonLibrary.Jackson)
                .end();

        restConfiguration().component("servlet").bindingMode(RestBindingMode.auto);

        rest()
                .get("weather/{city}")
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .outType(WeatherDto.class)
                .to("direct:get-weather-data")
                .post("/weather")
                .type(WeatherDto.class)
                .to("direct:save-weather-data")
                .get("weather")
                .to("direct:get-all-weather-data")
                .delete("/weather/{city}")
                .to("direct:delete-weather-data")
                .patch("/weather/{city}")
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .type(WeatherDto.class)
                .to("direct:update-weather-data");

        rest()
                .post("/prueba")
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .type(Prueba.class)
                .to("direct:agregar-prueba");

        from("direct:agregar-prueba")
                .log("LLega mani")
                .process(pruebaProcessor);

        rest()
                .post("/inventario/productos/")
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .produces("application/json")
                .type(Producto.class)
                .to("direct:agregar-producto");

        rest("/inventario/productos/")
                .put("/{id_producto}")
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .type(Producto.class)
                .to("direct:actualizar-producto");

        rest("/inventario/productos/")
                .delete("/{id_producto}")
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .type(Producto.class)
                .to("direct:eliminar-producto");

        rest("/inventario/productos/")
                .get("/{id_producto}")
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .type(Producto.class)
                .to("direct:consultar-producto");

        rest("/inventario/productos/")
                .get()
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .type(Producto.class)
                .to("direct:consultar-productos");

        from("direct:consultar-productos")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.setProperty("accion", "consultarProductos");

                    }
                })
                .process(productoProcessor);

        from("direct:consultar-producto")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.setProperty("accion", "consultar");
                    }
                })
                .process(productoProcessor);

        from("direct:eliminar-producto")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.setProperty("accion", "eliminar");
                    }
                })
                .process(productoProcessor);

        from("direct:actualizar-producto")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.setProperty("accion", "actualizar");

                    }
                })
                .process(productoProcessor);




        from("direct:agregar-producto")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))

                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange)  {

                        exchange.setProperty("accion", "agregar");


                     //   throw new RuntimeException("Error interno del servidor");

                    }
                })
                .process(productoProcessor);

        from("direct:get-weather-data")
                .process(getWeatherProcessor);

        from("direct:get-all-weather-data")
                .process(getAllWeatherDataProcessor);

        from("direct:save-weather-data")
                .process(saveWeatherAndSetToExchangeProcessor);

        from("direct:delete-weather-data")
                .process(deleteWeatherProcessor);

        from("direct:update-weather-data")
                .process(updateWeatherProcessor);

    }
}
