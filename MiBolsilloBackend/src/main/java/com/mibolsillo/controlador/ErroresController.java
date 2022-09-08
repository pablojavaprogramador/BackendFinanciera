package com.mibolsillo.controlador;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.bolsillo.model.error.YaRegistradoException;

import javassist.NotFoundException;

import com.bolsillo.model.error.AutorizacionException;
import com.bolsillo.model.error.BadRequestAlertException;
import com.bolsillo.model.error.ErrorMessage;
import com.bolsillo.model.error.ModeloNotFoundException;

import java.rmi.ServerException;
import java.time.LocalDateTime;
import java.util.Date;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
//@ControllerAdvice
public class ErroresController extends ResponseEntityExceptionHandler {

	
	
	@ExceptionHandler(ModeloNotFoundException.class)
	public ResponseEntity<ErrorMessage> ModeloNotFoundException(ModeloNotFoundException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(
        HttpStatus.NOT_FOUND.value(),
        new Date(),
        "Datos Ingresados Invalidos",
        ex.getMessage().toString(),
        request.getDescription(false));
    return new ResponseEntity<ErrorMessage>(message,HttpStatus.NOT_FOUND);
	}

	
	@ExceptionHandler(YaRegistradoException.class)
	public ResponseEntity<ErrorMessage> YaRegistradoException(YaRegistradoException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(
        HttpStatus.BAD_REQUEST.value(),
        new Date(),
        "Datos Ingresados Invalidos",
        ex.getMessage().toString(),
        request.getDescription(false));
    return new ResponseEntity<ErrorMessage>(message,HttpStatus.BAD_REQUEST);
	}

	
	
	@ExceptionHandler(BadRequestAlertException.class)
	public ResponseEntity<ErrorMessage> BadRequestAlertException(BadRequestAlertException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(
        HttpStatus.NOT_FOUND.value(),
        new Date(),
        "Datos Ingresados Invalidos",
        ex.getMessage().toString(),
        request.getDescription(false));
    return new ResponseEntity<ErrorMessage>(message,HttpStatus.NOT_FOUND);


	}
	

	 @ExceptionHandler(MethodArgumentTypeMismatchException.class)
	 @ResponseStatus(value = HttpStatus.BAD_REQUEST)
	  public ResponseEntity<ErrorMessage> MethodArgumentTypeMismatchException(Exception ex, WebRequest request) {
		  
		  if(  ex.getMessage().contains("NumberFormatException") ){
			  ErrorMessage message = new ErrorMessage(
				        HttpStatus.NOT_FOUND.value(),
				        new Date(),
				        "Datos Ingresados Invalidos",
				       "Tipo de dato invalido" ,
				        request.getDescription(false));
				    return new ResponseEntity<ErrorMessage>(message,HttpStatus.BAD_REQUEST);
		  }else if(ex.getMessage().contains("HttpRequestMethodNotSupportedException")) {
			  ErrorMessage message = new ErrorMessage(
				        HttpStatus.NOT_FOUND.value(),
				        new Date(),
				        "Datos Ingresados Invalidos",
				       "Tipo de dato invalido" ,
				        request.getDescription(false));
				    return new ResponseEntity<ErrorMessage>(message,HttpStatus.NOT_FOUND);
		  }
		  ErrorMessage message = new ErrorMessage(
			        HttpStatus.BAD_REQUEST.value(),
			        new Date(),
			        ex.getMessage().toString(),
			       ex.getCause().toString() ,
			        request.getDescription(false));
			    return new ResponseEntity<ErrorMessage>(message,HttpStatus.BAD_REQUEST);
		
	 }
	 
	 

    
		  @ExceptionHandler(Exception.class)
		// @ResponseStatus(value = HttpStatus.BAD_REQUEST)
		  public ErrorMessage ExceptionHandler(Exception ex, WebRequest request) {
			  
			  if(  ex.getMessage().contains("NumberFormatException") || ex instanceof IllegalArgumentException || ex instanceof HttpRequestMethodNotSupportedException || ex instanceof IllegalStateException
			          || ex.getMessage().contains("InvalidFormatException") || ex.getMessage().contains("JsonParseException") || ex.getMessage().contains("JsonMappingException")
			          || ex instanceof MissingServletRequestParameterException 
			          ){
					ErrorMessage message = new ErrorMessage(
					        HttpStatus.BAD_REQUEST.value(),
					        new Date(),
					        "Datos Ingresados Invalidos",
					        ex.getMessage().toString(),
					        request.getDescription(false));
					    return message;
			  }else if (ex.getMessage().contains("ConstraintViolationException") ) {
				  
					ErrorMessage message = new ErrorMessage(
					        HttpStatus.BAD_REQUEST.value(),
					        new Date(),
					        "Datos Ingresados Invalidos",
					        "Usuario o Correo ya Existente",
					        request.getDescription(false));
					    return message; 

				  
			  }else if (ex.getMessage().contains("org.springframework.web.bind.MethodArgumentNotValidException") ) {
				  
					ErrorMessage message = new ErrorMessage(
					        HttpStatus.BAD_REQUEST.value(),
					        new Date(),
					        "Datos Ingresados Invalidos",
					        "Usuario o Correo ya Existente",
					        request.getDescription(false));
					    return message; 

				  
			  }
				ErrorMessage message = new ErrorMessage(
				        HttpStatus.BAD_REQUEST.value(),
				        new Date(),
				        "Datos Ingresados Invalidos",
				        ex.getMessage().toString(),
				        request.getDescription(false));
				return message;
			
		  }
		  
		  
		  
			
			@ExceptionHandler(ServerException.class)
			@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
			public ResponseEntity<ErrorMessage> ServerException(ServerException ex, WebRequest request) {
				ErrorMessage message = new ErrorMessage(
		        HttpStatus.INTERNAL_SERVER_ERROR.value(),
		        new Date(),
		        "Error en el Servidor",
		        ex.getMessage().toString(),
		        request.getDescription(false));
		    return new ResponseEntity<ErrorMessage>(message,HttpStatus.INTERNAL_SERVER_ERROR);


			}
			
		  
		  
		  
		  
		  
//		    @ExceptionHandler(Exception.class)
//		    public final ResponseEntity<ErrorMessage> handleAllExceptions(Exception ex) {
//		       
//		        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		    }
//
//		    @ExceptionHandler(BadRequestException.class)
//		    public final ResponseEntity<ErrorMessage> handleBadRequest(BadRequestException ex) {
//		     
//		        return new ResponseEntity<null>(rspErr, HttpStatus.BAD_REQUEST);
//		    }
//
//		    @ExceptionHandler(AutorizacionException.class)
//		    public final ResponseEntity<ErrorMessage> handleUnauthorized(AutorizacionException ex) {
//		      
//		    }
//
//		    @ExceptionHandler(NotFoundException.class)
//		    public final ResponseEntity<RespuestaError> handleNotFound(NotFoundException ex) {
//		        return new ResponseEntity<RespuestaError>(null, HttpStatus.NOT_FOUND);
//		    }
//
//		    @ExceptionHandler(ServerException.class)
//		    public final ResponseEntity<RespuestaError> handleServerError(ServerException ex) {
//		       
//		        return new ResponseEntity<RespuestaError>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		    }

}
