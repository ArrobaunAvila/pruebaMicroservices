package com.neoris.pruebamicroservices.model.entity;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Setter
@Entity
@Table(name = "moviment")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date")
    private Date fecha;

    @Column(name = "type_moviment")
    private String tipoMovimiento;

    @Column(nullable = true)
    private String valor_movimiento;

    @Column(nullable = true)
    private String saldo_disponible;

    private String detalle;

    @ManyToOne
    @JoinColumn(name = "id_cuenta")
    private Cuenta cuenta;


}
