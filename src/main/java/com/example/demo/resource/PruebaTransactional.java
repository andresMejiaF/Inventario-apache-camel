package com.example.demo.resource;


import com.example.demo.dao.PruebaRepository;
import com.example.demo.dto.Prueba;
import com.example.demo.dto.WeatherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;

@Component
@Transactional
public class PruebaTransactional {

    @Autowired
    private PruebaRepository repository;

    public void guardar(Prueba dto) {
        repository.save(dto);
    }
}
