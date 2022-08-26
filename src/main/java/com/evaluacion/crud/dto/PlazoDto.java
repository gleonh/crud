package com.evaluacion.crud.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class PlazoDto {

    @NotBlank
    private String nombre;
    @Min(0)
    private int plazo;
    @Min(0)
    private Float interesSemanalNormal;
    @Min(0)
    private Float interesSemanalPuntual;

    public PlazoDto() {
    }

    public PlazoDto(@NotBlank String nombre, @Min(0) int plazo, @Min(0) Float interesSemanalNormal, @Min(0) Float interesSemanalPuntual) {
        this.nombre = nombre;
        this.plazo = plazo;
        this.interesSemanalNormal = interesSemanalNormal;
        this.interesSemanalPuntual = interesSemanalPuntual;
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Float getInteresSemanalNormal() {
		return interesSemanalNormal;
	}

	public void setInteresSemanalNormal(Float interesSemanalNormal) {
		this.interesSemanalNormal = interesSemanalNormal;
	}

	public Float getInteresSemanalPuntual() {
		return interesSemanalPuntual;
	}

	public void setInteresSemanalPuntual(Float interesSemanalPuntual) {
		this.interesSemanalPuntual = interesSemanalPuntual;
	}

	public int getPlazo() {
		return plazo;
	}

	public void setPlazo(int plazo) {
		this.plazo = plazo;
	}

	
}
