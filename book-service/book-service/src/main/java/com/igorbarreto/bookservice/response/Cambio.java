package com.igorbarreto.bookservice.response;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

public class Cambio implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String from;

    private String to;

    private Double conversionFactor;

    private Double convertedValue;

    private String environment;

    public Cambio(Long id, String from, String to, Double conversionFactor, Double convertedValue, String environment) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionFactor = conversionFactor;
        this.convertedValue = convertedValue;
        this.environment = environment;
    }

    public Cambio() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Double getConversionFactor() {
        return conversionFactor;
    }

    public void setConversionFactor(Double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public Double getConvertedValue() {
        return convertedValue;
    }

    public void setConvertedValue(Double convertedValue) {
        this.convertedValue = convertedValue;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cambio cambio)) return false;

        if (getId() != null ? !getId().equals(cambio.getId()) : cambio.getId() != null) return false;
        if (getFrom() != null ? !getFrom().equals(cambio.getFrom()) : cambio.getFrom() != null) return false;
        if (getTo() != null ? !getTo().equals(cambio.getTo()) : cambio.getTo() != null) return false;
        if (getConversionFactor() != null ? !getConversionFactor().equals(cambio.getConversionFactor()) : cambio.getConversionFactor() != null)
            return false;
        if (getConvertedValue() != null ? !getConvertedValue().equals(cambio.getConvertedValue()) : cambio.getConvertedValue() != null)
            return false;
        return getEnvironment() != null ? getEnvironment().equals(cambio.getEnvironment()) : cambio.getEnvironment() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getFrom() != null ? getFrom().hashCode() : 0);
        result = 31 * result + (getTo() != null ? getTo().hashCode() : 0);
        result = 31 * result + (getConversionFactor() != null ? getConversionFactor().hashCode() : 0);
        result = 31 * result + (getConvertedValue() != null ? getConvertedValue().hashCode() : 0);
        result = 31 * result + (getEnvironment() != null ? getEnvironment().hashCode() : 0);
        return result;
    }

}
