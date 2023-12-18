package br.com.restfull.unittests.mapper.mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.restfull.model.Person;
import br.com.restfull.vo.v1.PersonValueObject;

public class MockPerson {
	public Person mockEntity() {
		return mockEntity(0);
	}

	public PersonValueObject mockVO() {
		return mockVO(0);
	}

	public List<Person> mockEntityList() {
		List<Person> persons = new ArrayList<Person>();
		for (int i = 0; i < 14; i++) {
			persons.add(mockEntity(i));
		}
		return persons;
	}

	public List<PersonValueObject> mockVOList() {
		List<PersonValueObject> persons = new ArrayList<>();
		for (int i = 0; i < 14; i++) {
			persons.add(mockVO(i));
		}
		return persons;
	}

	public Person mockEntity(Integer number) {
		Person person = new Person(); 
		person.setAddress("Address Test " + number);
		person.setFirstName("First Name Test " + number);
		person.setGender(((number % 2) == 0) ? "Male" : "Female");
		person.setId(number.longValue());
		person.setLastName("Last Name Test " + number);
		return person;
	}

	public PersonValueObject mockVO(Integer number) {
		PersonValueObject person = new PersonValueObject();
		person.setAddress("Address Test " + number);
		person.setFirstName("First Name Test " + number);
		person.setGender(((number % 2) == 0) ? "Male" : "Female");
		person.setKey(number.longValue());
		person.setLastName("Last Name Test " + number);
		return person;
	}

}
