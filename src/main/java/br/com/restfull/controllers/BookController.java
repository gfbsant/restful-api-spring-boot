package br.com.restfull.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.restfull.services.BookService;
import br.com.restfull.vo.v1.BookValueObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/books/v1")
@Tag(name = "Books", description = "Endpoints to Manage Books")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = {"application/json", "application/xml", "application/x-yaml"})
	@Operation(summary = "Add a book", description = "Add a new book the table Book", tags = "Books", 
		responses = {
				@ApiResponse(description = "Success", responseCode = "200", 
						content = @Content(mediaType = "application/json, application/x-yaml, application/xml", 
						schema = @Schema(implementation = BookValueObject.class))), 
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content), 
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content), 
				@ApiResponse(description = "Internal Error", responseCode = "500" , content = @Content), 
	})
	public BookValueObject create(@RequestBody BookValueObject book) {
		return bookService.create(book);
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Delete a book", description = "Delete a book based on a ID", tags = "Books", 
		responses = {
				@ApiResponse(description = "No content", responseCode = "204", content = @Content), 
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content), 
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content), 
				@ApiResponse(description = "Internal Error", responseCode = "500" , content = @Content), 
	})
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		bookService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
	@Operation(summary = "Finds all people", description = "Returns all people that exists on the table Book", 
		tags = "Books", 
		responses = {
				@ApiResponse(description = "Success", responseCode = "200", 
						content = @Content(mediaType = "application/json, application/x-yaml, application/xml", 
						array = @ArraySchema(schema = @Schema(implementation = BookValueObject.class)))), 
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content), 
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content), 
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content), 
				@ApiResponse(description = "Internal Error", responseCode = "500" , content = @Content)
	})
	public List<BookValueObject> getAll() {
		return bookService.getAll();
	}

	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	@Operation(summary = "Finds a book", description = "Returns a book based on a ID", tags = "Books", 
		responses = {
				@ApiResponse(description = "Success", responseCode = "200", 
						content = @Content(mediaType = "application/json, application/x-yaml, application/xml", 
						schema = @Schema(implementation = BookValueObject.class))), 
				@ApiResponse(description = "No Content", content = @Content, responseCode = "204"), 
				@ApiResponse(description = "Bad Request", content = @Content, responseCode = "400"), 
				@ApiResponse(description = "Unauthorized", content = @Content, responseCode = "401"), 
				@ApiResponse(description = "Not Found", content = @Content, responseCode = "404"), 
				@ApiResponse(description = "Internal Error", content = @Content, responseCode = "500"), 
	})
	public BookValueObject getById(@PathVariable(value = "id") Long id) {
		return bookService.getById(id);
	}
	

	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	@Operation(summary = "Edit a book", description = "Edit a book based on a json, xml or yaml representation", 
		tags = "Books", 
		responses = {
				@ApiResponse(description = "Success", responseCode = "200", 
						content = @Content(mediaType = "application/json, application/x-yaml, application/xml", 
						schema = @Schema(implementation = BookValueObject.class))), 
				@ApiResponse(description = "Bad Request", content = @Content, responseCode = "400"), 
				@ApiResponse(description = "Unauthorized", content = @Content, responseCode = "401"), 
				@ApiResponse(description = "Not Found", content = @Content, responseCode = "404"), 
				@ApiResponse(description = "Internal Error", content = @Content, responseCode = "500"), 
	})
	public BookValueObject update(@RequestBody BookValueObject book) {
		return bookService.update(book);
	}

}
