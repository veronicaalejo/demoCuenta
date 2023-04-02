package com.cuenta.cuentaAplication.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="Cuenta")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long numeroCuenta;
    private long idUsuario;

    private double saldo;
    private String estadoCuenta; //active, hold
    private String tipoMoneda; //BO, USD


}
