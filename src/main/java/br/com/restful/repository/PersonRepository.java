package br.com.restful.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.restful.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
	
	Optional<Person> getByFirstName(String name);
	
}
