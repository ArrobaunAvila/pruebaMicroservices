package dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RequestBodyClientUpdate {

    private String password;
    private String estado;
    private PersonaUpdate info;


    @Builder
    @AllArgsConstructor
    @Getter
    @NoArgsConstructor
    public static class PersonaUpdate{
        private String edad;
        private String nombre;
        private String identification;
        private String direccion;
        private String telefono;
    }
}
