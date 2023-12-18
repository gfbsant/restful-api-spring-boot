package br.com.restfull.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.restfull.controllers.PersonController;
import br.com.restfull.exceptions.ResourceNotFoundException;
import br.com.restfull.mapper.MappingUtil;
import br.com.restfull.model.Person;
import br.com.restfull.repository.PersonRepository;
import br.com.restfull.vo.v1.PersonValueObject;

@Service
public class PersonService {

	private Logger logger = Logger.getLogger(PersonService.class.getName());

	@Autowired
	private PersonRepository repository;

	public PersonValueObject create(PersonValueObject person) {
		Person entity = MappingUtil.parseObject(person, Person.class);
		person = MappingUtil.parseObject(repository.save(entity), PersonValueObject.class);
		person.add(linkTo(methodOn(PersonController.class).getById(person.getKey())).withSelfRel());
		return person;
	}

	public void delete(Long id) {
		logger.info("Deleting a person with ID: " + id);
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Person not found with the id: " + id));
		repository.delete(entity);
	}

	public List<PersonValueObject> getAll() {
		logger.info("Finding all people");
		List<Person> entityList = repository.findAll();
		List<PersonValueObject> valueObjectList = MappingUtil.parseListObjects(entityList, PersonValueObject.class);
		valueObjectList.stream().forEach((valueObject) -> valueObject
				.add(linkTo(methodOn(PersonController.class).getById(valueObject.getKey())).withSelfRel()));
		return valueObjectList;
	}

	public PersonValueObject getById(Long id) {
		logger.info("Finding a person with ID: " + id);
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Person not found with the id: " + id));
		logger.info("Person found!");
		PersonValueObject person = MappingUtil.parseObject(entity, PersonValueObject.class);
		person.add(linkTo(methodOn(PersonController.class).getById(id)).withSelfRel());
		return person;
	}

	public PersonValueObject update(PersonValueObject person) {
		logger.info("Editing a person with ID: " + person.getKey());
		Person entity = repository.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Person not found"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		person = MappingUtil.parseObject(repository.save(entity), PersonValueObject.class);
		person.add(linkTo(methodOn(PersonController.class).getById(person.getKey())).withSelfRel());
		return person;
	}

}
