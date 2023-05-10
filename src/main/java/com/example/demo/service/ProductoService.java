package com.example.demo.service;

import com.example.demo.dao.ProductoRepository;
import com.example.demo.dao.PruebaRepository;
import com.example.demo.dto.Producto;
import com.example.demo.dto.Prueba;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repository;

    public void guardarProducto(Producto dto) {
        repository.save(dto);
    }
}
