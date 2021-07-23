package com.converter.controller;

import com.converter.model.Currency;
import com.converter.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class CurrencyController {

    private CurrencyService currencyService;
    private List<Currency> countedCurrencies = new ArrayList<>();

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public List<Currency> getCurrenciesValues() {
        return currencyService.getCurrencies();
    }

    @GetMapping("/count")
//    public List<Currency> countRateByUah(@RequestParam String code, @RequestParam Float value) {
    public String countRateByUah(@RequestParam String fromCurrencyCode, @RequestParam String toCurrencyCode, @RequestParam Float value) {
        currencyService.getCurrencies();
        return currencyService.countRateByUah(fromCurrencyCode, toCurrencyCode, value);
    }
}
