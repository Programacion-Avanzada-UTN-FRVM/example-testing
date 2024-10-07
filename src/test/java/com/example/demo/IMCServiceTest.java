package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class IMCServiceTest {
    private final IMCService imcService = new IMCService();

    @Test
    void testCalcularIMC_Exito() {
        double peso = 70.0;
        double altura = 1.75;
        double imc = imcService.calcularIMC(peso, altura);
        assertEquals (22.86, imc, 0.01);
    }

    @Test
    void testCalcularIMC_AlturaCero() {
        double peso = 70.0;
        double altura = 0;
        assertThrows(IllegalArgumentException.class, () -> imcService.calcularIMC(peso, altura));
    }

    @Test
    void testCalcularIMC_PesoNegativo() {
        double peso = -70.0;
        double altura = 1.75;
        assertThrows(IllegalArgumentException.class, () -> imcService.calcularIMC(peso, altura));
    }


    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)  // El test fallará si toma más de 500ms
    void testCalcularIMC_ExitosoTimeout() {
        // Arrange
        double peso = 70.0;
        double altura = 1.75;

        // Act
        double imc = imcService.calcularIMC(peso, altura);

        // Assert
        assertEquals(22.86, imc, 0.01);
    }

    @Test
    void testCalcularIMC_DentroDelTiempoEsperado() {
        // Arrange
        double peso = 70.0;
        double altura = 1.75;

        // Medimos el tiempo antes de la ejecución
        long startTime = System.nanoTime();

        // Act
        double imc = imcService.calcularIMC(peso, altura);

        // Medimos el tiempo después de la ejecución
        long endTime = System.nanoTime();

        // Calculamos el tiempo en milisegundos
        long duration = (endTime - startTime) / 1_000_000;  // Convertir a milisegundos

        // Assert
        assertTrue(duration < 500, "La ejecución del método tomó demasiado tiempo: " + duration + " ms");
    }


    @ParameterizedTest
    @CsvSource({
            "70.0, 1.75, 22.86",  // Caso 1: peso y altura válidos
            "80.0, 1.80, 24.69",  // Caso 2: otro peso y altura válidos
            "60.0, 1.65, 22.04",  // Caso 3: otro conjunto de valores válidos
            "90.0, 1.90, 24.93"   // Caso 4: otro conjunto de valores válidos
    })
    void testCalcularIMC_Parametrizado(double peso, double altura, double imcEsperado) {
        // Act
        double imcCalculado = imcService.calcularIMC(peso, altura);

        // Assert
        assertEquals(imcEsperado, imcCalculado, 0.01);  // Delta de 0.01 para la comparación de números en coma flotante
    }

    // Fuente de datos para el test
    static Stream<Arguments> provideIMCData() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(70.0, 1.75, 22.86),
                org.junit.jupiter.params.provider.Arguments.of(80.0, 1.80, 24.69),
                org.junit.jupiter.params.provider.Arguments.of(60.0, 1.65, 22.04),
                org.junit.jupiter.params.provider.Arguments.of(90.0, 1.90, 24.93)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIMCData")
    void testCalcularIMC_ParametrizadoConMethodSource(double peso, double altura, double imcEsperado) {
        // Act
        double imcCalculado = imcService.calcularIMC(peso, altura);

        // Assert
        assertEquals(imcEsperado, imcCalculado, 0.01);
    }



    @Test
    void testCalcularIMC_PesoCero() {
        assertThrows(IllegalArgumentException.class, () -> imcService.calcularIMC(0.0, 1.75));
    }

    @Test
    void testCalcularIMC_PesoExtremadamenteAlto() {
        double imc = imcService.calcularIMC(500.0, 2.0);
        assertTrue(imc > 100);  // Asegurarse de que el cálculo del IMC funciona para pesos altos
    }
    @Test
    void testCalcularIMC_AlturaNegativa() {
        assertThrows(IllegalArgumentException.class, () -> imcService.calcularIMC(70.0, -1.75));
    }
}
