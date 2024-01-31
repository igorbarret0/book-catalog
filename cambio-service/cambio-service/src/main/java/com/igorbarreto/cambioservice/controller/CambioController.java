package com.igorbarreto.cambioservice.controller;

import com.igorbarreto.cambioservice.model.Cambio;
import com.igorbarreto.cambioservice.repository.CambioRepository;

import com.igorbarreto.cambioservice.service.CambioService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
@RequestMapping("/cambio-service")
public class CambioController {
    @Autowired
    private CambioService cambioService;


    @GetMapping("/{amount}/{to}") // @GetMapping("/{amount}/{from}/{to}")
    public Cambio getCambio(
            @PathVariable(value = "amount") Double amount,
            @PathVariable(value = "to") String to
            ) {

        return cambioService.getCambio(amount, to);

    }


}
