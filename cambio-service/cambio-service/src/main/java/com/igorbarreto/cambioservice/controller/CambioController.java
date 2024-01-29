package com.igorbarreto.cambioservice.controller;

import com.igorbarreto.cambioservice.model.Cambio;
import com.igorbarreto.cambioservice.repository.CambioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
    private Environment environment;


    @Autowired
    private CambioRepository cambioRepository;

    @GetMapping("/{amount}/{from}/{to}")
    public Cambio getCambio(
            @PathVariable(value = "amount") BigDecimal amount,
            @PathVariable(value = "from") String from,
            @PathVariable(value = "to") String to
            ) {

        var cambio = cambioRepository.findByFromAndTo(from, to);
        if (cambio == null) {
            throw new RuntimeException("Currency not supported");
        }

        var port = environment.getProperty("local.server.port");
        BigDecimal conversionFactor = cambio.getConversionFactor();
        BigDecimal convertedValue = conversionFactor.multiply(amount);

        cambio.setConvertedValue(convertedValue.setScale(2, RoundingMode.CEILING));
        cambio.setEnvironment(port);

        return cambio;


    }


}
