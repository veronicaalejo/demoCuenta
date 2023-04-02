package com.cuenta.cuentaAplication.repository;

import com.cuenta.cuentaAplication.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
}
