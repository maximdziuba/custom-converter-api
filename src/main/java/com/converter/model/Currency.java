package com.converter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class Currency {
    private String name;
    private Double rateByUah;
    private String currencyCode;

    public Currency(Double rateByUah, String currencyCode) {
        this.rateByUah = rateByUah;
        this.currencyCode = currencyCode;
    }
}
