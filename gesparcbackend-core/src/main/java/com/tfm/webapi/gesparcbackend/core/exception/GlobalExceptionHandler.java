package com.tfm.webapi.gesparcbackend.core.exception;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.tfm.webapi.gesparcbackend.model.GenericResponse;
import com.tfm.webapi.gesparcbackend.model.GenericResponse.GenericResponseBuilder;
import com.tfm.webapi.gesparcbackend.model.GenericResponse.Status;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<GenericResponse> handleException(final Exception e, final WebRequest request) {
		log.error("Erreur technique : {}", e.getMessage(), e);
		final GenericResponseBuilder response = GenericResponse.builder().status(Status.FAILURE);
		response.errors(Arrays.asList("Erreur technique, veuillez contacter l'administrateur !"));
		return new ResponseEntity<>(response.build(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
