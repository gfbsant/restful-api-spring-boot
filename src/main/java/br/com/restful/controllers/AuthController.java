package br.com.restful.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.restful.services.AuthService;
import br.com.restful.vo.v1.security.AccountCredentialsValueObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Authentication Endpoint", description = "Endpoint to Manage Authentication")
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService service;

	@PostMapping(value = "/signin")
	@Operation(summary = "Authenticates a user and returns a token")
	public ResponseEntity<?> signIn(@RequestBody AccountCredentialsValueObject data) {
		if (checkIfParamsIsEmptyOrNull(data)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
		}
		ResponseEntity<?> token = service.signin(data);
		if (token == null)
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
		return token;
	}

	@PutMapping(value = "/refresh/{username}")
	@Operation(summary = "Refresh token for authenticated user and returns a token")
	public ResponseEntity<?> refresh(@PathVariable("username") String username, @RequestHeader("Authorization") String refreshToken) {

		if (checkIfParamsIsEmptyOrNull(username, refreshToken)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
		}
		ResponseEntity<?> token = service.refreshToken(username, refreshToken);
		if (token == null)
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
		return token;
	}

	private boolean checkIfParamsIsEmptyOrNull(String username, String refreshToken) {
		return username == null || username.isBlank() || refreshToken == null || refreshToken.isBlank();
	}

	private boolean checkIfParamsIsEmptyOrNull(AccountCredentialsValueObject data) {
		return data == null || data.getUserName() == null || data.getPassword() == null
				|| data.getUserName().isBlank() || data.getPassword().isBlank();
	}

}
