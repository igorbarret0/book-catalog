package com.igorbarreto.cambioservice.service;

import com.igorbarreto.cambioservice.model.Cambio;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CambioService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    String baseUrl = "https://economia.awesomeapi.com.br/json/last";

    public Cambio getCambio(Double amount, String to) {

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
