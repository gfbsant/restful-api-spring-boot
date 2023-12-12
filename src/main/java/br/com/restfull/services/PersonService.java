package br.com.restfull.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.restfull.converters.NumberConverter;
import br.com.restfull.model.Person;

@Service
public class PersonService {

	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonService.class.getName());

	public Person findById(String id) {
		logger.info("Finding a person");
		Person person = new Person();
		person.setId(NumberConverter.convertToLong(id));
		person.setFirstName("Joao");
		person.setLastName("Silva");
		person.setAddress("Curitiba-Parana-Brasil");
		person.setGender("Masc");
		return person;
	}

	public List<Person> findAll() {
		logger.info("Finding all people");
		List<Person> allPerson = new ArrayList<Person>();
		for (int i = 1; i <= 8; i++) {
			Person p = generateMockPerson(i);
			allPerson.add(p);
		}
		return allPerson;
	}
	
	public Person addPerson(Person person) {
		logger.info("Adding a person");
		return person;
	}
	
	public Person editPerson(Person person) {
		logger.info("Editing a person");
		return person;
	}
	
	public void deletePerson(String id) {
		logger.info("Deleting a person");
	}

	private Person generateMockPerson(int i) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Primeiro nome " + i);
		person.setLastName("Ultimo nome " + i);
		person.setAddress("Endereco numero " + i);
		person.setGender(i % 2 == 0 ? "Masc" : "Fem");
		return person;
	}

}
