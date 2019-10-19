package com.circulosiete.cursos.microservicios.business;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class DuplicateEntityException extends RuntimeException {

  private final String entityName;
  private final Serializable identifier;

  public DuplicateEntityException(String message, String entityName, Serializable identifier) {
    super(message);
    this.entityName = entityName;
    this.identifier = identifier;
  }

  public DuplicateEntityException(String entityName, Serializable identifier) {
    this("La entidad ya se encuentra definida.", entityName, identifier);
  }
}
