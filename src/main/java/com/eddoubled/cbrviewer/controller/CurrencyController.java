package com.eddoubled.cbrviewer.controller;

import com.eddoubled.cbrviewer.service.CurrencyService;
import io.codejournal.maven.xsd2java.Valuta;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

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
        try {
            return new ResponseEntity<>(currencyService.uploadCurrency(), HttpStatus.OK);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
