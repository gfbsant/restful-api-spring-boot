package br.com.restfull.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s";
	private final AtomicLong id = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World!") String name) {
		Long targetId = id.incrementAndGet();
		String targetName = String.format(template, name);
		Greeting targetGreeting = new Greeting(targetId, targetName);
		return targetGreeting;
	}

}

class Greeting {

	private final Long id;
	private final String content;

	public Greeting(Long id, String content) {
		super();
		this.id = id;
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

}