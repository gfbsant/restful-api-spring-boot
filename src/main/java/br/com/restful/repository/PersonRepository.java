package br.com.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.restful.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
