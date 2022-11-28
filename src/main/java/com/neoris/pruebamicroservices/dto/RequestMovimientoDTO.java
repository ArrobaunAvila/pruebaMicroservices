package com.neoris.pruebamicroservices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class RequestMovimientoDTO {
    private String numero_cuenta;
    private String tipo_movimiento;
    private String valor_movimiento;

}
