package br.com.restfull.repository;

import org.springframework.data.jpa.repository.*;

import br.com.restfull.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
