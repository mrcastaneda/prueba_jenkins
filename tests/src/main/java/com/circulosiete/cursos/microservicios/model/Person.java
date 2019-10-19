package com.circulosiete.cursos.microservicios.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Builder
@Slf4j
public class Person {
  private String id;
  private String name;
  private String email;

  // Esta anotación sirve para indicarle a Lombok que permita este constructor.
  // Debido a que en esta clase usamos la anotación @Builder
  @Tolerate
  public Person() {
    log.info("Creando persona");
  }

  public static Person from(String id, String name, String email) {
    return Person.builder()
      .id(id)
      .name(name)
      .email(email)
      .build();
  }
}
