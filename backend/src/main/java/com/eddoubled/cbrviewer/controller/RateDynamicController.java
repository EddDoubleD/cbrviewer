package com.eddoubled.cbrviewer.controller;

import com.eddoubled.cbrviewer.model.dto.response.dynamics.DynamicsResponse;
import com.eddoubled.cbrviewer.model.jaxb2.ValCursDynamics;
import com.eddoubled.cbrviewer.service.RateDynamicService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("api/v1/dynamic")
@Slf4j
public class RateDynamicController {
    RateDynamicService rateDynamicService;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<DynamicsResponse> getDynamic(@RequestParam String from,
                                                       @RequestParam String to,
                                                       @RequestParam String id) {
        try {
            return new ResponseEntity<>(rateDynamicService.getDynamics(from, to, id), HttpStatus.OK);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
