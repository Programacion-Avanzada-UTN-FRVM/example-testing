package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@WebMvcTest(IMCController.class)
public class IMCControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IMCService imcService;

    @Test
    void testCalcularIMC() throws Exception {
        IMCRequest request = new IMCRequest();
        request.setPeso(70.0);
        request.setAltura(1.75);

        when(imcService.calcularIMC(70.0, 1.75)).thenReturn(22.86);

        mockMvc.perform(post("/imc")
                        .contentType("application/json")
                        .content("{\"peso\": 70.0, \"altura\": 1.75}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.imc", is(22.86)))



                .andExpect(jsonPath("$.clasificacion", is("Normal")));
    }





}
