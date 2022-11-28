package com.neoris.pruebamicroservices.model.repository;

import com.neoris.pruebamicroservices.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente , Long> {

}
