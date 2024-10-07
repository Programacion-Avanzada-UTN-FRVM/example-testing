package com.example.demo;

public class IMCResponse {
    private double imc;
    private String clasificacion;

    public IMCResponse(double imc, String clasificacion) {
        this.imc = imc;
        this.clasificacion = clasificacion;
    }

    // Getters y Setters
    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }
}
