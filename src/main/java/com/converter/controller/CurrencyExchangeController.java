package com.converter.controller;

import com.converter.exception.EmptyCurrencyFieldException;
import com.converter.service.CurrencyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CurrencyExchangeController {

    private CurrencyService currencyService;
    private String fromCurrency = "";
    private String toCurrency = "";
    private String value = "0";

    public CurrencyExchangeController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public String exchange(Model model) {
        model.addAttribute("fromCurrency", this.fromCurrency);
        model.addAttribute("toCurrency", this.toCurrency);
        model.addAttribute("value", this.value);
        System.out.println(fromCurrency + " " + toCurrency + " " + value);
        return "main";
    }

    @PostMapping
    public String addCurrenciesForExchanging(@RequestParam String fromCurrency, @RequestParam String toCurrency, @RequestParam String value, Model model) {
        this.fromCurrency = fromCurrency.toLowerCase();
        this.toCurrency = toCurrency.toLowerCase();
        if (value == "") {
            throw new EmptyCurrencyFieldException("Поле не може бути порожнім");
        }
        this.value = currencyService.countRateByUah(fromCurrency.toLowerCase(), toCurrency.toLowerCase(), Float.parseFloat(value));
        model.addAttribute("fromCurrency", this.fromCurrency);
        model.addAttribute("toCurrency", this.toCurrency);
        model.addAttribute("value", this.value);
        return "redirect:/";
    }
}
