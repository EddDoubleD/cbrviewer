package com.eddoubled.cbrviewer.controller;

import com.eddoubled.cbrviewer.model.dto.response.dynamics.DynamicsResponse;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("api/v1/dynamic")
@Slf4j
public class RateDynamicController {
    RateDynamicService rateDynamicService;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<DynamicsResponse>> getDynamics(@RequestParam String from,
                                                              @RequestParam String to,
                                                              @RequestParam String id) {

        final List<DynamicsResponse> result = new ArrayList<>();
        List<String> ids = Arrays.asList(id.split(","));
        ids.forEach(i -> {
            try {
                result.add(rateDynamicService.getDynamics(from, to, i));
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        });

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
