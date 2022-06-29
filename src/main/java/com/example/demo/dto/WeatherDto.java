package com.example.demo.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class WeatherDto implements Serializable {
    static int counter = 1;
    @Id
    @Builder.Default
    private int id = counter++;
    @NonNull
    private String city;
    private String temp;
    private String unit;
    @Builder.Default
    private String receivedTime = new Date().toString();

    public void updateWeatherData(WeatherDto newOne) {
        if (Objects.nonNull(newOne.city)) this.city = newOne.city;
        if (Objects.nonNull(newOne.temp)) this.temp = newOne.temp;
        if (Objects.nonNull(newOne.unit)) this.unit = newOne.unit;
        this.receivedTime = newOne.receivedTime;
    }
}
