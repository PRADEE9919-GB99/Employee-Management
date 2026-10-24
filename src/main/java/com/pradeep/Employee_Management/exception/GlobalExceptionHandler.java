
package com.pradeep.Employee_Management.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@Autowired
	private Environment environment;

	@ExceptionHandler(EmployeeException.class)
	public ResponseEntity<ErrorInfo> handleEmployeeException(EmployeeException ex) {

		String errorInfo = environment.getProperty(ex.getMessage(), ex.getMessage());

		ErrorInfo error = new ErrorInfo(HttpStatus.NOT_FOUND.value(), errorInfo);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class, ConstraintViolationException.class })
	public ResponseEntity<ErrorInfo> handleValidationException(Exception ex) {

		String errorInfo;

		if (ex instanceof MethodArgumentNotValidException validationEx) {

			errorInfo = validationEx.getBindingResult().getFieldErrors().stream()
					.map(fieldError -> fieldError.getDefaultMessage()).findFirst()
					.orElseGet(() -> environment.getProperty("VALIDATION.ERROR"));

		} else if (ex instanceof ConstraintViolationException validationEx) {

			errorInfo = validationEx.getConstraintViolations().stream().map(violation -> violation.getMessage())
					.findFirst().orElseGet(() -> environment.getProperty("VALIDATION.ERROR"));

		} else {
			errorInfo = environment.getProperty("VALIDATION.ERROR");
		}

		ErrorInfo error = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), errorInfo);

		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> handleGeneralException(Exception ex) {

		String errorInfo = environment.getProperty("GENERAL.ERROR");

		ErrorInfo error = new ErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorInfo);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
}
