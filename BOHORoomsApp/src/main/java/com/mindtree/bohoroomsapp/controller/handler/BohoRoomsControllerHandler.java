package com.mindtree.bohoroomsapp.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.bohoroomsapp.dto.ErrorDTO;
import com.mindtree.bohoroomsapp.dto.ResponseBody;
import com.mindtree.bohoroomsapp.exception.BohoRoomsAppException;

@RestControllerAdvice
public class BohoRoomsControllerHandler {

	@ExceptionHandler(BohoRoomsAppException.class)
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
