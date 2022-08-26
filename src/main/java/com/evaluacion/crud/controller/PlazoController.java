package com.evaluacion.crud.controller;

import com.evaluacion.crud.dto.Mensaje;
import com.evaluacion.crud.dto.PlazoDto;
import com.evaluacion.crud.dto.ProductoDto;
import com.evaluacion.crud.entity.Plazo;
import com.evaluacion.crud.entity.Producto;
import com.evaluacion.crud.service.PlazoService;
import com.evaluacion.crud.service.ProductoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; 

import java.util.List;

@RestController
@RequestMapping("/plazo")    
@CrossOrigin(origins = "*")
public class PlazoController { 

    @Autowired
    PlazoService plazoService;

    @GetMapping("/lista")
    public ResponseEntity<List<Plazo>> list(){
        List<Plazo> list = plazoService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody PlazoDto plazoDto){
        if(StringUtils.isBlank(plazoDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(plazoService.existsByNombre(plazoDto.getNombre()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        
        if(plazoDto.getInteresSemanalNormal()==null || plazoDto.getInteresSemanalNormal()<0 )
            return new ResponseEntity(new Mensaje("la tasa de interes (normal) debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if(plazoDto.getInteresSemanalPuntual()==null || plazoDto.getInteresSemanalPuntual()<0 )
            return new ResponseEntity(new Mensaje("la tasa de interes (puntual) debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
         
        Plazo plazo = new Plazo(plazoDto.getNombre(), plazoDto.getPlazo(), plazoDto.getInteresSemanalNormal(), plazoDto.getInteresSemanalPuntual());
        plazoService.save(plazo);
        return new ResponseEntity(new Mensaje("plazo creado"), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody PlazoDto plazoDto){
        if(!plazoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(plazoService.existsByNombre(plazoDto.getNombre()) && plazoService.getByNombre(plazoDto.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(plazoDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(plazoDto.getInteresSemanalNormal()==null || plazoDto.getInteresSemanalNormal()<0 )
            return new ResponseEntity(new Mensaje("la tasa de interes (normal) debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if(plazoDto.getInteresSemanalPuntual()==null || plazoDto.getInteresSemanalPuntual()<0 )
            return new ResponseEntity(new Mensaje("la tasa de interes (puntual) debe ser mayor que 0"), HttpStatus.BAD_REQUEST);

        Plazo plazo = plazoService.getOne(id).get();
        plazo.setNombre(plazoDto.getNombre());
        plazo.setPlazo(plazoDto.getPlazo());
        plazo.setInteresSemanalNormal(plazoDto.getInteresSemanalNormal());
        plazo.setInteresSemanalPuntual(plazoDto.getInteresSemanalPuntual());
        plazoService.save(plazo);
        return new ResponseEntity(new Mensaje("plazo actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!plazoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        
        plazoService.delete(id);
        return new ResponseEntity(new Mensaje("plazo eliminado"), HttpStatus.OK);
    }
    
    @GetMapping("/detallePlazo/{id}")
    public ResponseEntity<Plazo> getById(@PathVariable("id") int id){
        if(!plazoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Plazo plazo = plazoService.getOne(id).get();
        return new ResponseEntity(plazo, HttpStatus.OK);
    }

    @GetMapping("/detallePlazoNombre/{nombre}")
    public ResponseEntity<Plazo> getByNombre(@PathVariable("nombre") String nombre){
        if(!plazoService.existsByNombre(nombre)) 
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Plazo plazo= plazoService.getByNombre(nombre).get();
        return new ResponseEntity(plazo, HttpStatus.OK);
    }


}
