package com.evaluacion.crud.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Plazo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private int plazo;
    private float interesSemanalNormal;
    private float interesSemanalPuntual;

    public Plazo() {
    }

    public Plazo(String nombre, int plazo, float interesSemanalNormal, float interesSemanalPuntual) {
        this.nombre = nombre;
        this.plazo = plazo;
        this.interesSemanalNormal = interesSemanalNormal;
        this.interesSemanalPuntual = interesSemanalPuntual;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getInteresSemanalNormal() {
		return interesSemanalNormal;
	}

	public void setInteresSemanalNormal(float interesNormal) {
		this.interesSemanalNormal = interesNormal;
	}

	public float getInteresSemanalPuntual() {
		return interesSemanalPuntual;
	}

	public void setInteresSemanalPuntual(float interesSemanalPuntual) {
		this.interesSemanalPuntual = interesSemanalPuntual;
	}

	public int getPlazo() {
		return plazo;
	}

	public void setPlazo(int plazo) {
		this.plazo = plazo;
	}

	
	
    
}
