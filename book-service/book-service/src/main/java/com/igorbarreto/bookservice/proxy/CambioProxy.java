package com.igorbarreto.bookservice.proxy;

import com.igorbarreto.bookservice.response.Cambio;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "cambio-service")
public interface CambioProxy {

    @GetMapping("/cambio-service/{amount}/{to}")
    public Cambio getCambio(
            @PathVariable(value = "amount") Double amount,
            //             @PathVariable(value = "from") String from,
            @PathVariable(value = "to") String to
    );

}
