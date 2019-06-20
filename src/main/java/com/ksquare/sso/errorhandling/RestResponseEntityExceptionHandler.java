package com.ksquare.sso.errorhandling;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler 
  extends ResponseEntityExceptionHandler {
 
    @ExceptionHandler(value = { RuntimeException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException exception, WebRequest request) {
        String bodyOfResponse = "Something went wrong";
        HttpStatus status = HttpStatus.NOT_FOUND;
        
        if(exception.getClass() == InvalidDataAccessApiUsageException.class) {
    		bodyOfResponse = "Invalid parameter in the path.";
    	}
    	if(exception.getClass() == NullPointerException.class) {
    		bodyOfResponse = "Invalid parameter in the path or in the body.";
    	}
    	if(exception.getClass() == DataIntegrityViolationException.class) {
    		bodyOfResponse = "Trying to duplicate a resource.";
    		status = HttpStatus.CONFLICT;
    	}
        
        return handleExceptionInternal(exception, bodyOfResponse, new HttpHeaders(), status, request);
    }
}
