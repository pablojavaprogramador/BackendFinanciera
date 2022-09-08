package com.bolsillo.model.error;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BadRequestAlertException extends RuntimeException  {
	public BadRequestAlertException(String mensaje) {
		super(mensaje);
	}
}
