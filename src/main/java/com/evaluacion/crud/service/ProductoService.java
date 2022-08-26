package com.evaluacion.crud.service;

import com.evaluacion.crud.entity.Producto;
import com.evaluacion.crud.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    public List<Producto> list(){
        return productoRepository.findAll();
    }

    public Optional<Producto> getOne(int id){
        return productoRepository.findById(id);
    }

    public Optional<Producto> getByNombre(String nombre){
        return productoRepository.findByNombre(nombre);
    }
    
    public Optional<Producto> getBySku(String sku){
        return productoRepository.findBySku(sku);
    }
    
    public Optional<Producto> getByNombreOrSku(String nombre, String sku){
        return productoRepository.findByNombreOrSku(nombre, sku);
    }

    public void  save(Producto producto){
        productoRepository.save(producto);
    }

    public void delete(int id){
        productoRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return productoRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return productoRepository.existsByNombre(nombre);
    }
    
    public boolean existsBySku(String nombre){
        return productoRepository.existsBySku(nombre);
    }
}
