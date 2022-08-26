package com.evaluacion.crud.controller;

import com.evaluacion.crud.dto.CotizacionDto;
import com.evaluacion.crud.dto.Mensaje;
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
@RequestMapping("/producto")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    ProductoService productoService;
    
    @Autowired
    PlazoService plazoService; 

    @GetMapping("/lista")
    public ResponseEntity<List<Producto>> lista(){
        List<Producto> list = productoService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/crear") 
    public ResponseEntity<?> create(@RequestBody ProductoDto productoDto){
        if(StringUtils.isBlank(productoDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(productoDto.getPrecio()==null || productoDto.getPrecio()<0 )
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if(productoService.existsByNombre(productoDto.getNombre()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(productoService.existsBySku(productoDto.getSku()))
            return new ResponseEntity(new Mensaje("ese sku ya existe"), HttpStatus.BAD_REQUEST);
        Producto producto = new Producto(productoDto.getNombre(), productoDto.getSku(), productoDto.getPrecio());
        productoService.save(producto);
        return new ResponseEntity(new Mensaje("producto creado"), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody ProductoDto productoDto){
        if(!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(productoService.existsByNombre(productoDto.getNombre()) && productoService.getByNombre(productoDto.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(productoService.existsBySku(productoDto.getSku()) && productoService.getBySku(productoDto.getSku()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(productoDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(productoDto.getSku()))
            return new ResponseEntity(new Mensaje("el sku es obligatorio"), HttpStatus.BAD_REQUEST);
        if(productoDto.getPrecio()==null || productoDto.getPrecio()<0 )
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);

        Producto producto = productoService.getOne(id).get();
        producto.setNombre(productoDto.getNombre());
        producto.setSku(productoDto.getSku());
        producto.setPrecio(productoDto.getPrecio());
        productoService.save(producto);
        return new ResponseEntity(new Mensaje("producto actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        productoService.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }
    
    @GetMapping("/detalleProducto/{id}")
    public ResponseEntity<Producto> getById(@PathVariable("id") int id){
        if(!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Producto producto = productoService.getOne(id).get();
        return new ResponseEntity(producto, HttpStatus.OK);
    }

    @GetMapping("/detalleProductoNombre/{nombre}")
    public ResponseEntity<Producto> getByNombre(@PathVariable("nombre") String nombre){
        if(!productoService.existsByNombre(nombre))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Producto producto = productoService.getByNombre(nombre).get();
        return new ResponseEntity(producto, HttpStatus.OK);
    }
    
    @GetMapping("/detalleProductoSku/{sku}")
    public ResponseEntity<Producto> getBySku(@PathVariable("sku") String sku){
        if(!productoService.existsBySku(sku))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Producto producto = productoService.getBySku(sku).get();
        return new ResponseEntity(producto, HttpStatus.OK);
    }

    @GetMapping("/cotizacionProducto/{nombresku}/{idPlazo}") 
    public ResponseEntity<Producto> cotizacionByNombreSku(@PathVariable("nombresku") String nombresku, @PathVariable("idPlazo")int idPlazo){
        if(productoService.getByNombreOrSku(nombresku, nombresku).isEmpty())
            return new ResponseEntity(new Mensaje("producto no existe"), HttpStatus.NOT_FOUND);
        if(!plazoService.existsById(idPlazo))
            return new ResponseEntity(new Mensaje("plazo no existe"), HttpStatus.NOT_FOUND);
        Producto producto = productoService.getByNombreOrSku(nombresku, nombresku).get();
        Plazo plazo = plazoService.getOne(idPlazo).get();
        Float abonoNormal = ((producto.getPrecio() * plazo.getInteresSemanalNormal()) + producto.getPrecio()) / plazo.getPlazo();
        Float abonoPuntual = ((producto.getPrecio() * plazo.getInteresSemanalPuntual()) + producto.getPrecio()) / plazo.getPlazo();
        
        CotizacionDto cotizacionDto = new CotizacionDto(producto.getSku(), plazo.getNombre(), abonoNormal, abonoPuntual );
        
        return new ResponseEntity(cotizacionDto, HttpStatus.OK); 
    }

}
