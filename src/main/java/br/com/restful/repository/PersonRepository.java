package br.com.restful.repository;

import org.springframework.data.jpa.repository.*;

import br.com.restful.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
