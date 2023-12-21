package br.com.restful.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.restful.mapper.MappingUtil;
import br.com.restful.model.Person;
import br.com.restful.repository.PersonRepository;
import br.com.restful.unittests.mapper.mocks.MockPerson;
import br.com.restful.vo.v1.PersonValueObject;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

	MockPerson input;

	@InjectMocks
	private PersonService service;

	@Mock
	PersonRepository repository;

	@Mock
	MappingUtil mappingUtil;

	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockPerson();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() {
		Person entity = input.mockEntity(1);
		entity.setId(1l);
		when(repository.findById(1l)).thenReturn(Optional.of(entity));

		PersonValueObject result = service.getById(1l);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());

		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals(result.getKey(), 1l);
		assertEquals(result.getFirstName(), "First Name Test 1");
		assertEquals(result.getLastName(), "Last Name Test 1");
		assertEquals(result.getAddress(), "Address Test 1");
		assertEquals(result.getGender(), "Female");
	}

	@Test
	void testAddPerson() {
		Person entity = input.mockEntity(1);
		entity.setId(1l);

		PersonValueObject person = input.mockVO(1);
		person.setKey(1l);

		when(repository.save(entity)).thenReturn(entity);

		PersonValueObject result = service.create(person);

		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());

		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals(result.getKey(), 1l);
		assertEquals(result.getFirstName(), "First Name Test 1");
		assertEquals(result.getLastName(), "Last Name Test 1");
		assertEquals(result.getAddress(), "Address Test 1");
		assertEquals(result.getGender(), "Female");
	}

	@Test
	void testDeletePerson() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		service.delete(1L);
	}

	@Test
	void testEditPerson() {
		Person entity = input.mockEntity(1);
		entity.setId(1l);

		Person persisted = entity;

		PersonValueObject person = input.mockVO(1);
		person.setKey(1l);

		when(repository.findById(1l)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);

		PersonValueObject result = service.update(person);

		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());

		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals(result.getKey(), 1l);
		assertEquals(result.getFirstName(), "First Name Test 1");
		assertEquals(result.getLastName(), "Last Name Test 1");
		assertEquals(result.getAddress(), "Address Test 1");
		assertEquals(result.getGender(), "Female");
	}

	@Test
	void testFindAll() {
		List<Person> list = input.mockEntityList();

		when(repository.findAll()).thenReturn(list);

		List<PersonValueObject> resultList = service.getAll();
		assertNotNull(resultList);
		assertEquals(resultList.size(), 14);
		int i = 0;
		for (PersonValueObject result : resultList) {
			assertNotNull(result);
			assertNotNull(result.getKey());
			assertNotNull(result.getLinks());

			assertTrue(result.toString().contains("links: [</api/person/v1/" + i + ">;rel=\"self\"]"));
			assertEquals(result.getKey(), Long.valueOf(i));
			assertEquals(result.getFirstName(), "First Name Test " + i);
			assertEquals(result.getLastName(), "Last Name Test " + i);
			assertEquals(result.getAddress(), "Address Test " + i);
			assertEquals(result.getGender(), (i % 2) == 0 ? "Male" : "Female");
			i++;
		}
	}

}
