package com.evaluacion.crud.service;

import com.evaluacion.crud.entity.Plazo;
import com.evaluacion.crud.entity.Producto;
import com.evaluacion.crud.repository.PlazoRepository;
import com.evaluacion.crud.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PlazoService {

    @Autowired
    PlazoRepository plazoRepository;

    public List<Plazo> list(){
        return plazoRepository.findAll();
    }

    public Optional<Plazo> getOne(int id){
        return plazoRepository.findById(id);
    }

    public Optional<Plazo> getByNombre(String nombre){
        return plazoRepository.findByNombre(nombre);
    }
    
    public void  save(Plazo plazo){
    	plazoRepository.save(plazo);
    }

    public void delete(int id){
    	plazoRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return plazoRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return plazoRepository.existsByNombre(nombre);
    }
    
}
