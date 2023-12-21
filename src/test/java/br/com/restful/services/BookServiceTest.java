package br.com.restful.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import br.com.restful.model.Book;
import br.com.restful.repository.BookRepository;
import br.com.restful.unittests.mapper.mocks.MockBook;
import br.com.restful.vo.v1.BookValueObject;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

	MockBook input;

	@InjectMocks
	private BookService service;

	@Mock
	BookRepository repository;

	@Mock
	MappingUtil mappingUtil;

	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockBook();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() throws ParseException {
		Book entity = input.mockEntity(1);
		entity.setId(1l);
		when(repository.findById(1l)).thenReturn(Optional.of(entity));

		BookValueObject result = service.getById(1l);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());

		assertTrue(result.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
		assertEquals(result.getKey(), 1l);
		assertEquals(result.getAuthor(), "Author Test 1");
		assertEquals(result.getTitle(), "Title Test 1");
		assertEquals(result.getPrice(), 1d);
		assertEquals(result.getLaunchDate(), new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-17"));
	}

	@Test
	void testAddBook() throws ParseException {
		Book entity = input.mockEntity(1);
		entity.setId(1l);

		BookValueObject book = input.mockVO(1);
		book.setKey(1l);

		when(repository.save(entity)).thenReturn(entity);

		BookValueObject result = service.create(book);

		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());

		assertTrue(result.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
		assertEquals(result.getKey(), 1l);
		assertEquals(result.getAuthor(), "Author Test 1");
		assertEquals(result.getTitle(), "Title Test 1");
		assertEquals(result.getPrice(), 1d);
		assertEquals(result.getLaunchDate(), new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-17"));
	}

	@Test
	void testDeleteBook() {
		Book entity = input.mockEntity(1);
		entity.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		service.delete(1L);
	}

	@Test
	void testEditBook() throws ParseException {
		Book entity = input.mockEntity(1);
		entity.setId(1l);

		Book persisted = entity;

		BookValueObject book = input.mockVO(1);
		book.setKey(1l);

		when(repository.findById(1l)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);

		BookValueObject result = service.update(book);

		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());

		assertTrue(result.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
		assertEquals(result.getKey(), 1l);
		assertEquals(result.getAuthor(), "Author Test 1");
		assertEquals(result.getTitle(), "Title Test 1");
		assertEquals(result.getPrice(), 1d);
		assertEquals(result.getLaunchDate(), new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-17"));
	}

	@Test
	void testFindAll() throws ParseException {
		List<Book> list = input.mockEntityList();

		when(repository.findAll()).thenReturn(list);

		List<BookValueObject> resultList = service.getAll();
		assertNotNull(resultList);
		assertEquals(resultList.size(), 14);
		int i = 0;
		for (BookValueObject result : resultList) {
			assertNotNull(result);
			assertNotNull(result.getKey());
			assertNotNull(result.getLinks());

			assertTrue(result.toString().contains("links: [</api/books/v1/" + i + ">;rel=\"self\"]"));
			assertEquals(result.getKey(), Long.valueOf(i));
			assertEquals(result.getAuthor(), "Author Test " + i);
			assertEquals(result.getTitle(), "Title Test " + i);
			assertEquals(result.getPrice(), Double.valueOf(i));
			assertEquals(result.getLaunchDate(), new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-17"));
			i++;
		}
	}

}
