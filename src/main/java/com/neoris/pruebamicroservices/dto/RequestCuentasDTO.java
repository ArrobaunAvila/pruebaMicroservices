package com.neoris.pruebamicroservices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
public class RequestCuentasDTO {
    private String numero_cuenta;
    private String tipo;
    private String saldo_inicial;
    private String estado;
    private String cliente;
}
