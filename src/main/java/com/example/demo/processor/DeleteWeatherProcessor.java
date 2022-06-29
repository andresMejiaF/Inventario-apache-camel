package com.example.demo.processor;

import com.example.demo.dto.WeatherDto;
import com.example.demo.resource.WeatherDataProvider;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteWeatherProcessor implements Processor {

    @Autowired
    private WeatherDataProvider weatherDataProvider;

    @Override
    public void process(Exchange exchange) throws Exception {
        String city = exchange.getMessage().getHeader("city", String.class);
        weatherDataProvider.deleteWeatherData(city);
    }
}
