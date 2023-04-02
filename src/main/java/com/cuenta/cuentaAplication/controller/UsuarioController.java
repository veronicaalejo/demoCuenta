package com.cuenta.cuentaAplication.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cuenta.cuentaAplication.model.Usuario;
import com.cuenta.cuentaAplication.repository.UsuarioRepository;


@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/usuarios")
	public  ResponseEntity<List<Usuario>> listausuarios() {

			List<Usuario> usuarioList = new ArrayList<>();
			usuarioRepository.findAll().forEach(usuarioList::add);
			
			if(usuarioList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(usuarioList, HttpStatus.OK);
	}

	@PostMapping("/usuarios")
	public ResponseEntity<Usuario> creaUsuario(@RequestBody Usuario usuario) {

		Optional<Usuario> ousuario = usuarioRepository.findByCi(usuario.getCi());
		if (ousuario.isPresent()) {
			throw new RuntimeException("Se registro correctamente el usuario");
		}
		Usuario usuarioObj = usuarioRepository.save(usuario);
		return new ResponseEntity<>(usuarioObj, HttpStatus.OK);
	}

}
