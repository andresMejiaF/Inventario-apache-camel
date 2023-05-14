package com.example.demo.service;

import com.example.demo.dao.ProductoRepository;
import com.example.demo.dao.PruebaRepository;
import com.example.demo.dto.Producto;
import com.example.demo.dto.Prueba;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repository;

    public void guardarProducto(Producto dto) {
        repository.save(dto);
    }

    public Producto actualizarProducto(Producto dto, Integer id){

        Optional<Producto> productoOptional = repository.findById(id);
        System.out.println("productoOptiona = " + productoOptional);

        if(productoOptional.isPresent()){
            Producto producto = productoOptional.get();
            producto.setCantidad(dto.getCantidad());
            producto.setPrecio(dto.getPrecio());
            producto.setNombre(dto.getNombre());
            producto.setDescripcion(dto.getDescripcion());
          return  repository.save(producto);
        }else{
            System.out.println("El id especificado no existe");
            return null;
        }
    }
    public Producto eliminarProducto(Integer id){
        Optional<Producto> productoOptional = repository.findById(id);
        if(productoOptional.isPresent()){
            Producto producto = productoOptional.get();
            repository.deleteById(id);
            return producto;
        }else{
            return null;
        }

    }

    public Producto consultarProducto(Integer id){
        Optional<Producto> productoOptional = repository.findById(id);

        if(productoOptional.isPresent()){
            Producto producto= productoOptional.get();
            return producto;
        }else{
            return null;
        }
    }

    public List<Producto> consultarProductos(){

        List<Producto> productos = repository.findAll();

        return productos;
    }
}
