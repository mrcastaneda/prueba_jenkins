package com.circulosiete.cursos.microservicios.business;

import com.circulosiete.cursos.microservicios.model.Person;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.circulosiete.cursos.microservicios.model.Person.from;

@Service
public class PeopleService {
  private List<Person> people = new ArrayList<>();

  @PostConstruct
  public void init() {
    people = Stream.of(
      from("domix", "Domingo", "domingo@circulosiete.com"),
      from("kanolato", "Miguel", "miguel@circulosiete.com"))
      .collect(Collectors.toList());
  }

  public List<Person> findPeople() {
    return Collections.unmodifiableList(people);
  }

  public void foo2() {
    try {
      foo();
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public void foo() throws FileNotFoundException {
    //....
  }

  public Person add(Person person) {
    findById(person.getId()).ifPresent(person1 -> {
      throw new DuplicateEntityException("Person", person.getId());
    });

    people.add(person);
    return person;
  }

  public Optional<Person> findById(String id) {
    return people.stream()
      .filter(person -> person.getId().equals(id))
      .findFirst();
  }

  public Person getById(String id) {
    return findById(id)
      .orElseThrow(() -> notFound(id));
  }

  public void delete(String id) {
    if (!people.removeIf(person -> person.getId().equals(id))) {
      throw notFound(id);
    }
  }

  public NotFoundException notFound(String id) {
    return new NotFoundException("Person", id);
  }
}
