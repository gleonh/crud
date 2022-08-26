package com.evaluacion.crud.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class CotizacionDto {

    @NotBlank
    private String sku;
    @NotBlank
    private String plazo;
    @Min(0)
    private Float interesSemanalNormal;
    @Min(0)
    private Float interesSemanalPuntual;

    public CotizacionDto() {
    }

    public CotizacionDto(@NotBlank String sku, @NotBlank String plazo, @Min(0) Float interesSemanalNormal, @Min(0) Float interesSemanalPuntual) {
        this.sku = sku;
        this.plazo = plazo;
        this.interesSemanalNormal = interesSemanalNormal;
        this.interesSemanalPuntual = interesSemanalPuntual;
    }

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getPlazo() {
		return plazo;
	}

	public void setPlazo(String plazo) {
		this.plazo = plazo;
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

	

	
}
