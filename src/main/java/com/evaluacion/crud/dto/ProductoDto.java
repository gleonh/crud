package com.evaluacion.crud.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ProductoDto {

    @NotBlank
    private String nombre;
    @NotBlank
    private String sku;
    @Min(0)
    private Float precio;

    public ProductoDto() {
    }

    public ProductoDto(@NotBlank String nombre, @NotBlank String sku,@Min(0) Float precio) {
        this.nombre = nombre;
        this.sku = sku;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
    
    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
}
