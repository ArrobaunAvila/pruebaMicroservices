package com.neoris.pruebamicroservices.model.repository;

import com.neoris.pruebamicroservices.model.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovimientoRepository extends JpaRepository<Movimiento, Long> {

}
