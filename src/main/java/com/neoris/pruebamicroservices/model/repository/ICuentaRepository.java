package com.neoris.pruebamicroservices.model.repository;

import com.neoris.pruebamicroservices.model.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICuentaRepository extends JpaRepository<Cuenta , Long> {

    Cuenta findByNumeroCuenta(String cuenta);
}
