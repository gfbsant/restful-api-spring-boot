package br.com.restful.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.restful.controllers.BookController;
import br.com.restful.exceptions.ResourceNotFoundException;
import br.com.restful.mapper.MappingUtil;
import br.com.restful.model.Book;
import br.com.restful.repository.BookRepository;
import br.com.restful.vo.v1.BookValueObject;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {

	private Logger logger = Logger.getLogger(BookService.class.getName());

	@Autowired
	private BookRepository repository;

	public BookValueObject create(BookValueObject book) {
		Book entity = MappingUtil.parseObject(book, Book.class);
		book = MappingUtil.parseObject(repository.save(entity), BookValueObject.class);
		book.add(linkTo(methodOn(BookController.class).getById(book.getKey())).withSelfRel());
		return book;
	}

	public void delete(Long id) {
		logger.info("Deleting a book with ID: " + id);
		Book entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found with the id: " + id));
		repository.delete(entity);
	}

	public List<BookValueObject> getAll() {
		logger.info("Finding all people");
		List<Book> entityList = repository.findAll();
		List<BookValueObject> valueObjectList = MappingUtil.parseListObjects(entityList, BookValueObject.class);
		valueObjectList.stream().forEach((valueObject) -> valueObject
				.add(linkTo(methodOn(BookController.class).getById(valueObject.getKey())).withSelfRel()));
		return valueObjectList;
	}

	public BookValueObject getById(Long id) {
		logger.info("Finding a book with ID: " + id);
		Book entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found with the id: " + id));
		logger.info("Book found!");
		BookValueObject book = MappingUtil.parseObject(entity, BookValueObject.class);
		book.add(linkTo(methodOn(BookController.class).getById(id)).withSelfRel());
		return book;
	}

	public BookValueObject update(BookValueObject book) {
		logger.info("Editing a book with ID: " + book.getKey());
		Book entity = repository.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Book not found"));
		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());
		book = MappingUtil.parseObject(repository.save(entity), BookValueObject.class);
		book.add(linkTo(methodOn(BookController.class).getById(book.getKey())).withSelfRel());
		return book;
	}

}
