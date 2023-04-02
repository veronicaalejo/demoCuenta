package com.cuenta.cuentaAplication.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Transaccion")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long idUsuario;
    private long numeroCuenta;

    private double monto;
    private String tipoOperacion; //deposito, retiro
    private String tipoMoneda; //BO, USD
    private Date fechaOperacion; //SYSDATE
    private boolean estadoTransaccion; //TRUE : HABILITADO, FALSE:DESHABILITADO

}
