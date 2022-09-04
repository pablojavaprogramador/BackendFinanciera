package com.mibolsillo.controlador;

import io.swagger.annotations.Api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.mibolsillo.model.ErrorMessage;
import com.mibolsillo.model.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@RestControllerAdvice
public class ErroresController {

	@ExceptionHandler(ResourceNotFoundException.class)
	  @ResponseStatus(value = HttpStatus.NOT_FOUND)
	  public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
	    ErrorMessage message = new ErrorMessage(
	        HttpStatus.NOT_FOUND.value(),
	        new Date(),
	        ex.getMessage(),
	        request.getDescription(false));
	    
	    return message;
	  }
	  

	
	
	  @ExceptionHandler(Exception.class)
	  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
	  public ErrorMessage globalExceptionHandler(Exception ex, WebRequest request) {
		  
		  if(  ex.getMessage().contains("NumberFormatException") || ex instanceof IllegalArgumentException || ex instanceof HttpRequestMethodNotSupportedException || ex instanceof IllegalStateException
		          || ex.getMessage().contains("InvalidFormatException") || ex.getMessage().contains("JsonParseException") || ex.getMessage().contains("JsonMappingException")
		          || ex instanceof MissingServletRequestParameterException || ex instanceof MethodArgumentNotValidException
		          ){
			  ErrorMessage message = new ErrorMessage(
				        HttpStatus.BAD_REQUEST.value(),
				        new Date(),
				        "Error en la Peticion:  "+ex.getMessage(),
				        request.getDescription(false));
				    return message;
		  }else if (ex.getMessage().contains("No value present") ) {
			  
			  ErrorMessage message = new ErrorMessage(
				        HttpStatus.NOT_FOUND.value(),
				        new Date(),
				        "No se encontro el Articulo",
				        request.getDescription(false));
				    return message; 
		  }
		  
		  ErrorMessage message = new ErrorMessage(
			        HttpStatus.BAD_GATEWAY.value(),
			        new Date(),
			        ex.getMessage(),
			        request.getDescription(false));
			    return message; 
		
	  }
    
	  
	

}
