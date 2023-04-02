package com.cuenta.cuentaAplication.controller;

import com.cuenta.cuentaAplication.model.Cuenta;
import com.cuenta.cuentaAplication.model.Usuario;
import com.cuenta.cuentaAplication.repository.CuentaRepository;
import com.cuenta.cuentaAplication.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CuentaController {

    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/cuentas")
    public ResponseEntity<List<Cuenta>> listaCuentas() {

            List<Cuenta> cuentaList = new ArrayList<>();
            cuentaRepository.findAll().forEach(cuentaList::add);

            if(cuentaList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cuentaList, HttpStatus.OK);
    }

    @GetMapping("/cuentas/{id}")
    public ResponseEntity<Cuenta> verCuentaSaldoById(@PathVariable Long id) {
        Optional<Cuenta> cuentaData = cuentaRepository.findById(id);
        if(cuentaData.isPresent()){
            return new ResponseEntity<>(cuentaData.get(), HttpStatus.OK);
        }
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @PostMapping("/cuentas")
    public ResponseEntity<Cuenta> creaCuenta(@RequestBody Cuenta cuenta) {
        Optional<Usuario> usuarioData = usuarioRepository.findById(cuenta.getIdUsuario());
        if(!usuarioData.isPresent()){
            throw new RuntimeException("El numero de identificador de usuario no existe");
        }
        cuenta.setEstadoCuenta("ACTIVE");
        cuenta.setSaldo(0);
        Cuenta cuentaObj = cuentaRepository.save(cuenta);
        return new ResponseEntity<>(cuentaObj, HttpStatus.OK);
    }

}
