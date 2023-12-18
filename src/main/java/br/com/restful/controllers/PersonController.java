package br.com.restful.controllers;

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

import br.com.restful.services.PersonService;
import br.com.restful.vo.v1.PersonValueObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/person/v1")
@Tag(name = "People", description = "Endpoints to Manage People")
public class PersonController {

	@Autowired
	private PersonService personService;

	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = {"application/json", "application/xml", "application/x-yaml"})
	@Operation(summary = "Add a person", description = "Add a new person the table Person", tags = "People", 
		responses = {
				@ApiResponse(description = "Success", responseCode = "200", 
						content = @Content(mediaType = "application/json, application/x-yaml, application/xml", 
						schema = @Schema(implementation = PersonValueObject.class))), 
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content), 
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content), 
				@ApiResponse(description = "Internal Error", responseCode = "500" , content = @Content), 
	})
	public PersonValueObject create(@RequestBody PersonValueObject person) {
		return personService.create(person);
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Delete a person", description = "Delete a person based on a ID", tags = "People", 
		responses = {
				@ApiResponse(description = "No content", responseCode = "204", content = @Content), 
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content), 
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content), 
				@ApiResponse(description = "Internal Error", responseCode = "500" , content = @Content), 
	})
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		personService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
	@Operation(summary = "Finds all people", description = "Returns all people that exists on the table Person", 
		tags = "People", 
		responses = {
				@ApiResponse(description = "Success", responseCode = "200", 
						content = @Content(mediaType = "application/json, application/x-yaml, application/xml", 
						array = @ArraySchema(schema = @Schema(implementation = PersonValueObject.class)))), 
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content), 
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content), 
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content), 
				@ApiResponse(description = "Internal Error", responseCode = "500" , content = @Content)
	})
	public List<PersonValueObject> getAll() {
		return personService.getAll();
	}

	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	@Operation(summary = "Finds a person", description = "Returns a person based on a ID", tags = "People", 
		responses = {
				@ApiResponse(description = "Success", responseCode = "200", 
						content = @Content(mediaType = "application/json, application/x-yaml, application/xml", 
						schema = @Schema(implementation = PersonValueObject.class))), 
				@ApiResponse(description = "No Content", content = @Content, responseCode = "204"), 
				@ApiResponse(description = "Bad Request", content = @Content, responseCode = "400"), 
				@ApiResponse(description = "Unauthorized", content = @Content, responseCode = "401"), 
				@ApiResponse(description = "Not Found", content = @Content, responseCode = "404"), 
				@ApiResponse(description = "Internal Error", content = @Content, responseCode = "500"), 
	})
	public PersonValueObject getById(@PathVariable(value = "id") Long id) {
		return personService.getById(id);
	}
	

	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	@Operation(summary = "Edit a person", description = "Edit a person based on a json, xml or yaml representation", 
		tags = "People", 
		responses = {
				@ApiResponse(description = "Success", responseCode = "200", 
						content = @Content(mediaType = "application/json, application/x-yaml, application/xml", 
						schema = @Schema(implementation = PersonValueObject.class))), 
				@ApiResponse(description = "Bad Request", content = @Content, responseCode = "400"), 
				@ApiResponse(description = "Unauthorized", content = @Content, responseCode = "401"), 
				@ApiResponse(description = "Not Found", content = @Content, responseCode = "404"), 
				@ApiResponse(description = "Internal Error", content = @Content, responseCode = "500"), 
	})
	public PersonValueObject update(@RequestBody PersonValueObject person) {
		return personService.update(person);
	}

}
