package com.cuenta.cuentaAplication.controller;

import java.io.IOException;
import java.security.SignatureException;
import java.sql.SQLDataException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class RestDefaultExceptionHandler extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> exception(DataIntegrityViolationException ex) {
		ex.printStackTrace();
		return ResponseEntity.internalServerError().body("Error en el servidor: " + (ex != null ? ex.getCause().getCause().getMessage() : ""));
	}

	@ExceptionHandler(SQLDataException.class)
	public ResponseEntity<Object> exception(SQLDataException ex) {
		ex.printStackTrace();
		return ResponseEntity.internalServerError().body("Error en el servidor: " + (ex != null ? ex.getCause().getCause().getMessage() : ""));
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<?> handleException(Exception ex) {
		ex.printStackTrace();
		return ResponseEntity.internalServerError().body("Error en el servidor: " + (ex != null ? ex.getCause().getCause().getMessage() : ""));
	}

	@ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<?> handleException(RuntimeException ex) {
		ex.printStackTrace();
		return ResponseEntity.badRequest().body("Error: " + (ex != null ? ex.getCause().getCause().getMessage() : ""));
	}

	@ExceptionHandler(IOException.class)
	public final ResponseEntity<?> handleException(IOException ex) {
		ex.printStackTrace();
		return ResponseEntity.internalServerError().body("Error en el servidor: " + (ex != null ? ex.getCause().getCause().getMessage() : ""));
	}

	@ExceptionHandler(SignatureException.class)
	public final ResponseEntity<?> handleException(SignatureException ex) {
		ex.printStackTrace();
		return ResponseEntity.internalServerError().body("Error en el servidor: " + (ex != null ? ex.getCause().getCause().getMessage() : ""));
	}

}