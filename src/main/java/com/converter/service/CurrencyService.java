package com.converter.service;

import com.converter.exception.NoSuchCurrencyException;
import com.converter.model.Currency;
import com.converter.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CurrencyService {
    private Parser parser;
    List<Currency> currencies = new ArrayList<>();
    private Map<String, String> currenciesNames = Map.ofEntries(
            Map.entry("uah", "Украинская гривна"),
            Map.entry("chf", "Юань"),
            Map.entry("eur", "Евро"),
            Map.entry("dkk", "Датская крона"),
            Map.entry("cad", "Канадский доллар"),
            Map.entry("usd", "Доллар"),
            Map.entry("rub", "Рубль"),
            Map.entry("byn", "Белорусский рубль"),
            Map.entry("czk", "Чешский крон"),
            Map.entry("pln", "Польский злотый")
    );

    @Autowired
    public CurrencyService(Parser parser) {
        this.parser = parser;
    }

    public List<Currency> getCurrencies() {
        for (Currency currency : parser.getCurrencies()) {
            String currencyCode = currency.getCurrencyCode();
            String currencyName = currenciesNames.get(currencyCode);
            currency.setName(currencyName);
            if (currenciesNames.containsKey(currencyCode)) {
                currencies.add(currency);
            }
        }
        return currencies;
    }

    //    public List<Currency> countRateByUah(String currencyCode, Float value) {
    public String countRateByUah(String fromCurrencyCode, String toCurrencyCode, Float value) {
        getCurrencies();
//        Currency fromFilteredCurrency = new Currency(1.0, "uah");
//        Currency toFilteredCurrency = new Currency(27.0, "usd");
        Currency fromFilteredCurrency = null;
        Currency toFilteredCurrency = null;
        for (Currency currency1 : currencies) {
            if (currency1.getCurrencyCode().equals(fromCurrencyCode)) {
                fromFilteredCurrency = currency1;
            }
        }
        for (Currency currency : currencies) {
            if (currency.getCurrencyCode().equals(toCurrencyCode)) {
                toFilteredCurrency = currency;
            }
        }

        if (fromFilteredCurrency == null || toFilteredCurrency == null) {
            throw new NoSuchCurrencyException("Не знайдено такої валюти:(");
        }
        java.util.Currency fromCurrencyInstance = java.util.Currency.getInstance(fromCurrencyCode.toUpperCase());
        String fromCurrencySymbol = fromCurrencyInstance.getSymbol();
        java.util.Currency toCurrencyInstance = java.util.Currency.getInstance(toCurrencyCode.toUpperCase());
        String toCurrencySymbol = toCurrencyInstance.getSymbol();
        Double foreignCurrencyRate = value / (toFilteredCurrency.getRateByUah() / fromFilteredCurrency.getRateByUah());
        String result = String.format("%.3f", value) + " " + fromCurrencySymbol + " => " + String.format("%.3f", foreignCurrencyRate) + " " + toCurrencySymbol;
        return result;
    }
}
