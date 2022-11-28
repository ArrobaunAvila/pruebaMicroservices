package com.neoris.pruebamicroservices.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Builder
@Setter
@NoArgsConstructor
@Getter
public class ResponseApiDto {

    private HttpStatus code;

    private boolean process;

    private String description;

    private Object data;
}
