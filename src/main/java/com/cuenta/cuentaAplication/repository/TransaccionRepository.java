package com.cuenta.cuentaAplication.repository;

import com.cuenta.cuentaAplication.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {

    List<Transaccion> findByNumeroCuenta(Long numCuenta);
}
