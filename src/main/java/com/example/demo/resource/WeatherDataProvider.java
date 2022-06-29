package com.example.demo.resource;

import com.example.demo.dao.WeatherRepository;
import com.example.demo.dto.WeatherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.*;

@Component
@Transactional
public class WeatherDataProvider {
    @Autowired
    private WeatherRepository repository;
    // private static Map<String, WeatherDto> weatherData = new HashMap<>();

    @PostConstruct
    public void initDB() {
        List<WeatherDto> weatherData = new ArrayList<>();
        weatherData.add(WeatherDto.builder()
                .city("London").temp("10")
                .unit("C")
                .build());
        weatherData.add(WeatherDto.builder()
                .city("China").temp("20")
                .unit("C")
                .build());
        weatherData.add(WeatherDto.builder()
                .city("Singapore").temp("29")
                .unit("C")
                .build());
        repository.saveAll(weatherData);
    }

    public WeatherDto getCurrentWeather(String city) {
        return repository.findByCityIgnoreCase(city.toUpperCase());
    }

    public void setCurrentWeather(WeatherDto dto) {
        dto.setReceivedTime(new Date().toString());
        repository.save(dto);
    }

    public List<WeatherDto> getAllWeatherData() {
        return repository.findAll();
    }

    public void deleteWeatherData(String city) {
        repository.deleteByCityIgnoreCase(city);

    }

    public void updateCity(String city, WeatherDto newOne) {
        WeatherDto dto = repository.findByCityIgnoreCase(city);
        dto.updateWeatherData(newOne);
    }
}
