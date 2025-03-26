package com.admin.exception;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
 
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
 
import feign.FeignException;
 
 
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
 
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		// Fill the code here
		ExceptionResponse e= new ExceptionResponse(LocalDate.now(),ex.getMessage(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString()); ;
		return new ResponseEntity<ExceptionResponse>(e,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(FeignException.class)
    public ResponseEntity<ExceptionResponse> handleFeignException(FeignException ex) {
        String errorMessage = "An unexpected error occurred while processing the request.";
        if (ex.status() == HttpStatus.NOT_FOUND.value()) {
            String message = ex.getMessage();  
            if (message != null && message.contains(":")) {
                errorMessage = message.substring(message.lastIndexOf(":") + 2).trim();  
                if (errorMessage.startsWith("[") && errorMessage.endsWith("]")) {
                    errorMessage = errorMessage.substring(1, errorMessage.length() - 1).trim();
                }
            }
        }
        else if (ex.status() == HttpStatus.SERVICE_UNAVAILABLE.value()) {
            errorMessage = "The service is temporarily unavailable. Please try again later.";
        }
        else {
            String feignErrorMessage = ex.getMessage();
            if (feignErrorMessage != null) {
                errorMessage = feignErrorMessage;
            }
        }
        ex.printStackTrace();
        ExceptionResponse er= new ExceptionResponse(LocalDate.now(), errorMessage, errorMessage, HttpStatus.INTERNAL_SERVER_ERROR.toString());
        return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
    }
 
	@ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<ExceptionResponse> handleNotFoundException(RuntimeException ex, WebRequest request) {
		ExceptionResponse e= new ExceptionResponse(LocalDate.now(),ex.getMessage(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString()); ;

		return new ResponseEntity<ExceptionResponse>(e , HttpStatus.NOT_FOUND);
	}
 
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
		HttpHeaders headers, HttpStatusCode status, WebRequest request) {	
		Map<String, String> map= new HashMap<String, String>();
//		List<FieldError> list = ex.getBindingResult().getFieldErrors();
//		map = list.stream().collect(Collectors.toMap(n->n.getField(), n->n.getDefaultMessage()));

		ex.getBindingResult().getFieldErrors().forEach(er->map.put(er.getField(),er.getDefaultMessage()));
		return new ResponseEntity<Object>(map,HttpStatus.BAD_REQUEST);
	}
}