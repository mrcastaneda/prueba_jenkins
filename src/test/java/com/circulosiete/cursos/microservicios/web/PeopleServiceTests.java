package com.circulosiete.cursos.microservicios.web;

import com.circulosiete.cursos.microservicios.business.NotFoundException;
import com.circulosiete.cursos.microservicios.business.PeopleService;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PeopleServiceTests {
  private PeopleService peopleService = new PeopleService();


  public  void setup() {
    peopleService.init();
  }

  @Test
  public void shouldFindAPersonById() throws Throwable {
    setup();
    assertTrue(peopleService.findById("domix").isPresent());
    //assertNotNull(peopleService.findById("domix"));
  }

  @Test(expected = NotFoundException.class)
  public void shouldFailDueNotFoundPerson() throws Throwable {
    setup();
    peopleService.getById("domis");
  }
}
