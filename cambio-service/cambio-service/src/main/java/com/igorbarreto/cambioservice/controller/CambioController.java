package com.igorbarreto.cambioservice.controller;

import com.igorbarreto.cambioservice.model.Cambio;
import com.igorbarreto.cambioservice.repository.CambioRepository;

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
    private Environment environment;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CambioRepository cambioRepository;

    String baseUrl = "https://economia.awesomeapi.com.br/json/last";

    @GetMapping("/{amount}/{to}") // @GetMapping("/{amount}/{from}/{to}")
    public Cambio getCambio(
            @PathVariable(value = "amount") Double amount,
            @PathVariable(value = "to") String to
            ) {


        var port = environment.getProperty("local.server.port");

        String currencyPair = "USD-" + to;

        String url = String.format("%s/%s", baseUrl, currencyPair);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);


        var response = responseEntity.getBody();

        JSONObject jsonObject = new JSONObject(response);

        var jsonObject1 = jsonObject.getJSONObject("USD" + to);

        String currentValue = jsonObject1.get("high").toString();

        Double conversionFactor = Double.valueOf(currentValue).doubleValue();

        Double convertedValue = conversionFactor * amount;


        Cambio newCambio = new Cambio();

        newCambio.setFrom("USD");
        newCambio.setTo(to);
        newCambio.setConversionFactor(conversionFactor);
        newCambio.setConvertedValue(convertedValue);
        newCambio.setEnvironment(port);

        return newCambio;


    }


}
