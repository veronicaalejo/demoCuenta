package com.cuenta.cuentaAplication.controller;

import com.cuenta.cuentaAplication.model.Cuenta;
import com.cuenta.cuentaAplication.model.Transaccion;
import com.cuenta.cuentaAplication.model.Usuario;
import com.cuenta.cuentaAplication.repository.CuentaRepository;
import com.cuenta.cuentaAplication.repository.TransaccionRepository;
import com.cuenta.cuentaAplication.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class Transaccioncontroller {

    @Autowired
    private TransaccionRepository transaccionRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CuentaRepository cuentaRepository;

    @GetMapping("/transacciones/{id}")
    public ResponseEntity<List<Transaccion>> verTransaccionByIdCuenta(@PathVariable Long id) {
        List<Transaccion> transaccionData = transaccionRepository.findByNumeroCuenta(id);
            return new ResponseEntity<>(transaccionData, HttpStatus.OK);
    }
    @PostMapping("/transacciones")
    public ResponseEntity<?> saveTransaccion(@RequestBody Transaccion transaccion) {

        Optional<Cuenta> ocuenta = cuentaRepository.findById(transaccion.getNumeroCuenta());

        if(!ocuenta.isPresent()){
            return ResponseEntity.badRequest().body("CUENTA INCORRECTA");
        }
        Cuenta cuenta = ocuenta.get();
        double operador = transaccion.getTipoOperacion().equals("DEPOSITO")? 1 : -1;
        double saldo = cuenta.getSaldo() + (transaccion.getMonto()  * operador); // -280+500 * +1
        String estadoCuenta = cuenta.getEstadoCuenta();
        double saldoActual = cuenta.getSaldo();
        if (operador == 1) {
            cuenta.setSaldo(saldo);
            if (saldo >= 0) {
                cuenta.setEstadoCuenta("ACTIVE");
            }
        } else if (cuenta.getEstadoCuenta() != "HOLD" && saldo < 0) {
            cuenta.setSaldo(saldo);
            cuenta.setEstadoCuenta("HOLD");
        } else if (cuenta.getEstadoCuenta() == "ACTIVE" && saldo > 0) {
            cuenta.setSaldo(saldo);
        }

        //cuentaRepository.save(cuenta);
        Transaccion tranccionObj = null;
        if (estadoCuenta == "HOLD" && transaccion.getTipoOperacion().equals("RETIRO")  ) {
            return ResponseEntity.badRequest().body("TRANSACCION INCORRECTA");
        }else if(estadoCuenta == "HOLD" && transaccion.getTipoOperacion().equals("DEPOSITO") && (saldoActual + transaccion.getMonto()) < 0  ) {
            return ResponseEntity.badRequest().body("TRANSACCION INCORRECTA2");
        }else{
            transaccion.setFechaOperacion(new Date());
            transaccion.setEstadoTransaccion(true);
            tranccionObj = transaccionRepository.save(transaccion);
            cuentaRepository.save(cuenta);
        }



        return new ResponseEntity<>(tranccionObj, HttpStatus.OK);
    }
}
