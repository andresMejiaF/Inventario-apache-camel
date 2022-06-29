package com.example.demo.processor;

import com.example.demo.resource.WeatherDataProvider;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.support.DefaultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllWeatherDataProcessor implements Processor {
    @Autowired
    private WeatherDataProvider weatherDataProvider;

    @Override
    public void process(Exchange exchange) throws Exception {
        Message message = new DefaultMessage(exchange.getContext());
        message.setBody(weatherDataProvider.getAllWeatherData());
        exchange.setMessage(message);
    }
}
