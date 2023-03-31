package com.eddoubled.cbrviewer.repository;

import com.eddoubled.cbrviewer.model.jaxb2.Valuta;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CurrencyRepository extends MongoRepository<Valuta.Item, String> {
    Optional<Valuta.Item> findByName(String name);
}
