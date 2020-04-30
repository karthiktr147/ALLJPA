package com.mindtree.kalingacompany.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.kalingacompany.dto.ErrorDTO;
import com.mindtree.kalingacompany.dto.ResponseBody;
import com.mindtree.kalingacompany.exception.KalingaCompanyAppException;

@RestControllerAdvice
public class BookingMovieControllerHandler {

//	@Autowired
//	MessageSource messageSource;									

	@ExceptionHandler(KalingaCompanyAppException.class)
	public ResponseEntity<?> errorHandler(Exception e) {
		return new ResponseEntity<ResponseBody<Void>>(
				new ResponseBody<Void>(null, new ErrorDTO(e.getMessage(), e.getCause()), "Error in Application", false),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		String errorMessaage = "";
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			errorMessaage += error.getDefaultMessage() + ", ";
		}

		return new ResponseEntity<ResponseBody<Void>>(
				new ResponseBody<Void>(null, new ErrorDTO(errorMessaage, ex.getCause()), "Error in Application", false),
				HttpStatus.BAD_REQUEST);

	}

}
