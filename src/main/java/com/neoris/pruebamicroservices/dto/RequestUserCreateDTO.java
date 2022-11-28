package com.neoris.pruebamicroservices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserCreateDTO {

    private String nombre;

    private String direccion;

    private String telefono;

    private String contraseña;

    private String estado;

}
