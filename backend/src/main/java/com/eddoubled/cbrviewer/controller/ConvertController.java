package com.eddoubled.cbrviewer.controller;

import com.eddoubled.cbrviewer.model.jaxb2.ValCurs;
import com.eddoubled.cbrviewer.service.ConvertService;
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
@RequestMapping("api/v1/convert")
@Slf4j
public class ConvertController {

    ConvertService convertService;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<ValCurs.Valute>> getAllCurrenciesRates() {
        return new ResponseEntity<>(convertService.getAllCurrenciesRates(), HttpStatus.OK);
    }

    @GetMapping("{key}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<ValCurs.Valute> getCurrencyRateByKey(@PathVariable String key) {
        Optional<ValCurs.Valute> currenciesRates = convertService.findById(key);
        return currenciesRates.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
