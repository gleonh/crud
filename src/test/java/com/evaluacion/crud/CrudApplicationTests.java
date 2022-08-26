package com.evaluacion.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.evaluacion.crud.controller.ProductoController;
import com.evaluacion.crud.dto.ProductoDto;
import com.evaluacion.crud.entity.Producto;
import com.evaluacion.crud.service.ProductoService;


@SpringBootTest
class CrudApplicationTests {

	@Autowired
    ProductoService productoService;
	
	@Test
	void listaProduto() {
		List<Producto> list = productoService.list();
		assertTrue(list.size() > 0); 
	}
	
	@Test
	void crearProduto() {
		Producto producto = new Producto("Producto 4", "004", 14.5f);
		productoService.save(producto);
		assertTrue(productoService.existsByNombre(producto.getNombre()));
	}
	
	
	

}
