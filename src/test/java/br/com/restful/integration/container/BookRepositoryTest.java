package br.com.restful.integration.container;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import br.com.restful.model.Book;
import br.com.restful.repository.BookRepository;
import br.com.restful.unittests.mapper.mocks.MockBook;

@Testcontainers
@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

	@Container
	@ServiceConnection
	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.1");

	@Autowired
	BookRepository repository;

	@BeforeEach
	void setUp() {
		MockBook mock = new MockBook();
		Book book = mock.mockEntity();
		try {
			repository.save(book);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	void connectionEstablished() {
		boolean isCreated = postgres.isCreated();
		boolean isRunning = postgres.isRunning();

		assertThat(isCreated).isTrue();
		assertThat(isRunning).isTrue();
	}

	@Test
	void shouldReturnBookById() {
		Book entity = repository.findById(0l).orElseThrow();
		assertThat(entity).isNotNull();
	}

}