package com.evaluacion.crud.repository;

import com.evaluacion.crud.entity.Plazo;
import com.evaluacion.crud.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlazoRepository extends JpaRepository<Plazo, Integer> {
    Optional<Plazo> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
    
}
