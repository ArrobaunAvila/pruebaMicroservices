package com.neoris.pruebamicroservices.model.entity;

import javax.persistence.*;
import  lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "account")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

   @Column(name = "account")
   private String numeroCuenta;

   @Column(name= "typeAccount")
   private String tipoCuenta;

   @Column(name = "balanceInitial")
   private String saldoInicial;

   @Column(name = "state")
   private String state;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "id_client")
   private Cliente cliente;

   @OneToMany(mappedBy = "cuenta", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private List<Movimiento> movimiento;

}
