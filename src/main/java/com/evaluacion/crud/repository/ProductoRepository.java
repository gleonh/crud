package com.evaluacion.crud.repository;

import com.evaluacion.crud.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Optional<Producto> findByNombre(String nombre);
    Optional<Producto> findBySku(String sku);
    boolean existsByNombre(String nombre);
    boolean existsBySku(String sku);
    
    Optional<Producto> findByNombreOrSku(String nombre, String Sku);
}
