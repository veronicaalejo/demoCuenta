package com.cuenta.cuentaAplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cuenta.cuentaAplication.model.Usuario;

import java.util.Optional;

@Repository  
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCi(String ci);
}
