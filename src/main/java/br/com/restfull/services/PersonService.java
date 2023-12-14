package br.com.restfull.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.restfull.converters.NumberConverter;
import br.com.restfull.exceptions.ResourceNotFoundException;
import br.com.restfull.model.Person;
import br.com.restfull.repository.PersonRepository;

@Service
public class PersonService {

	private Logger logger = Logger.getLogger(PersonService.class.getName());

	@Autowired
	private PersonRepository repository;

	public Person addPerson(Person person) {
		return repository.save(person);
	}

	public ResponseEntity<?> deletePerson(Long id) {
		logger.info("Deleting a person with ID: " + id);
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Person not found with the id: " + id));
		repository.delete(entity);
		return ResponseEntity.noContent().build();
	}

	public Person editPerson(Person person) {
		logger.info("Editing a person with ID: " + person.getId());
		Person entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Person not found with the id: " + person.getId()));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		return repository.save(entity);
	}

	public List<Person> findAll() {
		logger.info("Finding all people");
		List<Person> entityList = repository.findAll();
		return entityList;
	}

	public Person findById(Long id) {
		logger.info("Finding a person with ID: " + id);
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Person not found with the id: " + id));
		logger.info("Person found!");
		return entity;
	}

}
