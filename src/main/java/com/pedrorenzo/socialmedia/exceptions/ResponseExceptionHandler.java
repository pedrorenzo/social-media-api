package com.pedrorenzo.socialmedia.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(final Exception ex, final WebRequest request)
			throws Exception {
		return new ResponseEntity<ExceptionResponse>(
				new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false)),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(final Exception ex,
			final WebRequest request) throws Exception {
		return new ResponseEntity<ExceptionResponse>(
				new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false)),
				HttpStatus.NOT_FOUND);
	}

	@Override
	public final ResponseEntity<Object> handleMethodArgumentNotValid(
			final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		return new ResponseEntity<Object>(
				new ExceptionResponse(new Date(), "Validation failed", ex.getBindingResult().toString()),
				HttpStatus.BAD_REQUEST);
	}
	
}
