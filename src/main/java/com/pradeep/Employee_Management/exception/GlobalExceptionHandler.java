package com.pradeep.Employee_Management.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@Autowired
	private Environment environment;

	@ExceptionHandler(EmployeeException.class)
	public ResponseEntity<ErrorInfo> handleStudentException(EmployeeException ex) {

		ErrorInfo error = new ErrorInfo(HttpStatus.NOT_FOUND.value(), environment.getProperty(ex.getMessage()));

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class, ConstraintViolationException.class })
	public ResponseEntity<ErrorInfo> handleValidationException(Exception ex) {

		String errorInfo;

		if (ex instanceof MethodArgumentNotValidException) {
			MethodArgumentNotValidException validationEx = (MethodArgumentNotValidException) ex;

			errorInfo = validationEx.getBindingResult().getFieldErrors().stream()
					.map(error -> error.getDefaultMessage()).findFirst()
					.orElse(environment.getProperty(ex.getMessage()));

		} else {
			errorInfo = environment.getProperty(ex.getMessage());
		}

		ErrorInfo error = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), errorInfo);

		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> handleGeneralException(Exception ex) {

		ErrorInfo error = new ErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				environment.getProperty(ex.getMessage()));

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}

}
