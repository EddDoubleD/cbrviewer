package com.eddoubled.cbrviewer.repository;

import com.eddoubled.cbrviewer.model.jaxb2.ValCurs;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConvertRepository extends MongoRepository<ValCurs.Valute, String> {
}
