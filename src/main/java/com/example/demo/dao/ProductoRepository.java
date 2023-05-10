package com.example.demo.dao;

import com.example.demo.dto.Producto;
import com.example.demo.dto.Prueba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
