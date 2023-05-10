package com.example.demo.dao;

import com.example.demo.dto.Prueba;
import com.example.demo.dto.WeatherDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PruebaRepository  extends JpaRepository<Prueba, Integer> {



}
