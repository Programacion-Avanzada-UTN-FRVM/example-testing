package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/imc")
public class IMCController {

    @Autowired
    private IMCService imcService;

    @PostMapping
    public IMCResponse calcularIMC(@RequestBody IMCRequest request) {
        double imc = imcService.calcularIMC(request.getPeso(), request.getAltura());
        return new IMCResponse(imc, clasificarIMC(imc));
    }

    private String clasificarIMC(double imc) {
        if (imc < 18.5) return "Bajo peso";
        else if (imc < 24.9) return "Normal";
        else if (imc < 29.9) return "Sobrepeso";
        else return "Obesidad";
    }
}
