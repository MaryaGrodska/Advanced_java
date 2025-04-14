package com.advanced_java.HrodskaM.controller;

import org.springframework.ui.Model;
import com.advanced_java.HrodskaM.dto.CryptoCurrencyQuote;
import com.advanced_java.HrodskaM.service.CryptoCurrencyService;
import com.advanced_java.HrodskaM.service.ExcelService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CurrencyController {
    @Autowired
    private CryptoCurrencyService cryptoService;

    @Autowired
    private ExcelService excelService;

    @GetMapping("/search")
    public String searchCrypto(@RequestParam("name") String name, Model model) {
        List<CryptoCurrencyQuote> quotes = cryptoService.getQuotes(name.toLowerCase());
        model.addAttribute("quotes", quotes);
        model.addAttribute("currencyName", name);
        return "index";
    }

    @GetMapping("/download")
    public void downloadExcel(@RequestParam("name") String name, HttpServletResponse response) throws Exception {
        List<CryptoCurrencyQuote> quotes = cryptoService.getQuotes(name.toLowerCase());
        excelService.exportToExcel(quotes, response);
    }
}
