package com.circulosiete.cursos.microservicios.web;

import com.circulosiete.cursos.microservicios.business.DuplicateEntityException;
import com.circulosiete.cursos.microservicios.business.NotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(RuntimeException.class)
  public HttpEntity runtimeException(RuntimeException exception) {
    HashMap<String, String> response = new HashMap<>();
    response.put("message", exception.getMessage());
    response.put("cod_error", "756476");

    return ResponseEntity
      .status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(response);
  }

  @ExceptionHandler(NotFoundException.class)
  public HttpEntity dduplicateEntityException(NotFoundException exception) {
    HashMap<String, String> response = new HashMap<>();
    response.put("message", exception.getMessage());
    response.put("Entity", exception.getEntityName());
    response.put("Identifier", exception.getIdentifier().toString());

    return ResponseEntity
      .status(HttpStatus.NOT_FOUND)
      .body(response);
  }

  @ExceptionHandler(DuplicateEntityException.class)
  public HttpEntity duplicateEntityException(DuplicateEntityException exception) {
    HashMap<String, String> response = new HashMap<>();
    response.put("message", exception.getMessage());
    response.put("Entity", exception.getEntityName());
    response.put("Identifier", exception.getIdentifier().toString());

    return ResponseEntity
      .status(HttpStatus.CONFLICT)
      .body(response);
  }

}
