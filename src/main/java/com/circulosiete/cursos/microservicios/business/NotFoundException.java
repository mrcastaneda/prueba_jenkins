package com.circulosiete.cursos.microservicios.business;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class NotFoundException extends RuntimeException {
  private final String entityName;
  private final Serializable identifier;

  public NotFoundException(String message, String entityName, Serializable identifier) {
    super(message);
    this.entityName = entityName;
    this.identifier = identifier;
  }

  public NotFoundException(String entityName, Serializable identifier) {
    this("No se encontro a la entidad.", entityName, identifier);
  }
}
