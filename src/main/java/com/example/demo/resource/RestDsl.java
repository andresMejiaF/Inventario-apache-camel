package com.example.demo.resource;

import com.example.demo.dto.Producto;
import com.example.demo.dto.Prueba;
import com.example.demo.dto.WeatherDto;
import com.example.demo.processor.*;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
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




    @Override
    public void configure() throws Exception {
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
                .type(Producto.class)
                .to("direct:agregar-producto");

        from("direct:agregar-producto")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.setProperty("accion", "agregar");
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
