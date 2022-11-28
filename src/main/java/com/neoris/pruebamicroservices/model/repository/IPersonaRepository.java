package com.neoris.pruebamicroservices.model.repository;

import com.neoris.pruebamicroservices.model.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IPersonaRepository extends JpaRepository<Persona, Long> {
    Optional<Persona> findByNombre(String name);
}
