package com.example.demo.dao;

import com.example.demo.dto.WeatherDto;
import org.springframework.data.jpa.repository.JpaRepository; //.jpa.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherDto, Integer> {
    WeatherDto findByCityIgnoreCase(String city);
    void deleteByCityIgnoreCase(String city);
}
