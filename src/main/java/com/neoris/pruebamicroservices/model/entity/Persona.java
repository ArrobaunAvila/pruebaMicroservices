package com.neoris.pruebamicroservices.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String nombre;

     @Column(name = "gender")
    private String genero;

    @Column(name = "age")
    private String edad;

    @Column(name = "identification")
    private String identificacion;

    @Column(name = "address", nullable = false)
    private String direccion;

     @Column(name="cellphone", nullable = false)
     private String telefono;

     @OneToOne(mappedBy = "persona", fetch = FetchType.LAZY)
     private Cliente cliente;

}
