package com.eddoubled.cbrviewer.controller;

import com.eddoubled.cbrviewer.model.jaxb2.Valuta;
import com.eddoubled.cbrviewer.service.CurrencyService;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("api/v1/currency")
@Slf4j
public class CurrencyController {

    CurrencyService currencyService;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Valuta.Item>> getCurrency() {
        return new ResponseEntity<>(currencyService.getCurrency(), HttpStatus.OK);
    }

    @GetMapping("{key}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Valuta.Item> getCurrencyById(@PathVariable String key) {
        Optional<Valuta.Item> currency = currencyService.findById(key);
        if (currency.isPresent()) {
            return new ResponseEntity<>(currency.get(), HttpStatus.OK);
        }

        log.info("currency with id = {} not found", key);
        currency = currencyService.findByName(key);
        return currency.map(item -> new ResponseEntity<>(item, HttpStatus.OK)).orElseGet(
                () -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
