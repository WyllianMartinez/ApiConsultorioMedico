package br.com.unipar.apiconsultoriomedico.controllers;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.unipar.apiconsultoriomedico.exceptions.ApiErrorMessage;

@RestControllerAdvice
public class AplicattionControllerAdvice {
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrorMessage handleException(Exception ex) {
		return new ApiErrorMessage(ex.getMessage());
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrorMessage handleMethodArgumentNotValidException(
			MethodArgumentNotValidException ex) {
		
		ArrayList<String> listaErros = new ArrayList<>();
		
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			listaErros.add(error.getField() + ": "+error.getDefaultMessage());
		}
		
		return new ApiErrorMessage(listaErros);
	}
}
