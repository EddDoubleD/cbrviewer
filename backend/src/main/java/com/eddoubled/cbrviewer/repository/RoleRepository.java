package com.eddoubled.cbrviewer.repository;

import com.eddoubled.cbrviewer.model.ERole;
import com.eddoubled.cbrviewer.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
