package com.circulosiete.cursos.microservicios.web;

import com.circulosiete.cursos.microservicios.business.PeopleService;
import com.circulosiete.cursos.microservicios.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/people")
public class PeopleController {
  private final PeopleService peopleService;

  public PeopleController(PeopleService peopleService) {
    this.peopleService = peopleService;
  }

  @GetMapping
  public List<Person> people() {
    return peopleService.findPeople();
  }

  @GetMapping("/entity")
  public HttpEntity getPeople() {

    try {
      return ResponseEntity.ok(peopleService.findPeople());
    } catch (NullPointerException e) {
      //TODO: para el lunes
      log.error("error", e);
      //throw new RuntimeException(e);
      throw new RuntimeException();
    } catch (DataAccessException e) {
      Map data = Map.of("Error", e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(data);
    }
  }

  public String foo() {
    peopleService.foo2();

    return "chido";
  }

  @GetMapping("/{id}")
  public Person findById(
    @PathVariable("id") String id) {
    return peopleService.getById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Person post(
    @RequestBody Person person) {
    return peopleService.add(person);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(
    @PathVariable("id") String id) {
    peopleService.delete(id);
  }

}
