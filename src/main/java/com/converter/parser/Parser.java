package com.converter.parser;

import com.converter.model.Currency;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Data
@Component
public class Parser {

    private static String minfinUrl = "https://minfin.com.ua/currency/banks/";
    private Document html = getHtml();

    private static Document getHtml() {
        Document html = null;
        try {
            html = Jsoup.connect(minfinUrl).get();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return html;
    }

    private List<String> getRates() {
        List<String> rates = new ArrayList<>();
        Elements ratesElements = html.getElementsByClass("mfcur-nbu-full-wrap");
        ratesElements.forEach(element -> rates.add(getOnlyCurrencyCost(element)));
        return rates;
    }

    private String getOnlyCurrencyCost(Element element) {
        String currencyCost = element.text().substring(0, 7);
        return currencyCost;
    }

    private List<String> getCurrenciesNames() {
        List<String> names = new ArrayList<>();
        Elements namesElements = html.getElementsByClass("mfcur-table-cur");
        namesElements.forEach(element -> {
            Elements links = element.getElementsByTag("a");
            links.forEach(link -> {
                String buffer = link.attr("href").replace("currency/banks/", "");
                String name = buffer.replaceAll("/", "");
                names.add(name);
            });
        });
        return names;
    }

    public List<Currency> getCurrencies() {
        List<Currency> allCurrencies = new ArrayList<>();
        allCurrencies.add(new Currency(1d   , "uah"));
        for (int i = 0; i < getRates().size(); i++) {
            String currencyName = getCurrenciesNames().get(i);
            Double currencyRate = Double.parseDouble(getRates().get(i));
            allCurrencies.add(new Currency(currencyRate, currencyName));
        }
        return allCurrencies;
    }
}
