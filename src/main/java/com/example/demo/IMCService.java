package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class IMCService {

    public double calcularIMC(double peso, double altura) {
        if (altura <= 0 || peso <= 0.5) {
            throw new IllegalArgumentException("Altura y peso deben ser mayores a cero.");
        }
        return peso / (altura * altura);
    }
}
