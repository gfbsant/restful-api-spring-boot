package br.com.restfull.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.restfull.converters.NumberConverter;
import br.com.restfull.exceptions.UnsupportedMathOperationException;

@RestController
public class MathController {
	
	BasicMath math = new BasicMath();

	@GetMapping(value = "/sum/{numberOne}/{numberTwo}")
	public Double sum(@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) {

		if (NumberConverter.isNaN(numberOne) || NumberConverter.isNaN(numberTwo))
			throw new UnsupportedMathOperationException("Please set a numeric value!");

		return math.sum(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}

	@GetMapping(value = "/subtract/{numberOne}/{numberTwo}")
	public Double subtract(@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) {

		if (NumberConverter.isNaN(numberOne) || NumberConverter.isNaN(numberTwo))
			throw new UnsupportedMathOperationException("Please set a numeric value!");

		return math.subtract(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}

	@GetMapping(value = "/multiply/{numberOne}/{numberTwo}")
	public Double multiply(@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) {

		if (NumberConverter.isNaN(numberOne) || NumberConverter.isNaN(numberTwo))
			throw new UnsupportedMathOperationException("Please set a numeric value!");

		return math.multiply(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}

	@GetMapping(value = "/division/{numberOne}/{numberTwo}")
	public Double division(@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) {

		if (NumberConverter.isNaN(numberOne) || NumberConverter.isNaN(numberTwo))
			throw new UnsupportedMathOperationException("Please set a numeric value");

		return math.division(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}

	@GetMapping(value = "/average/{numberOne}/{numberTwo}")
	public Double average(@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) {

		if (NumberConverter.isNaN(numberOne) || NumberConverter.isNaN(numberTwo))
			throw new UnsupportedMathOperationException("Please set a numeric value!");

		return math.average(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}

	@GetMapping(value = "/square/{number}")
	public Double squareRoot(@PathVariable(value = "number") String number) {

		if (NumberConverter.isNaN(number))
			throw new UnsupportedMathOperationException("Please set a numeric value");

		return math.squareRoot(NumberConverter.convertToDouble(number));
	}

}

class BasicMath {

	public Double sum(Double numberOne, Double numberTwo) {
		return (numberOne) + (numberTwo);
	}

	public Double subtract(Double numberOne, Double numberTwo) {
		return (numberOne) - (numberTwo);
	}

	public Double multiply(Double numberOne, Double numberTwo) {
		return (numberOne) * (numberTwo);
	}

	public Double division(Double numberOne, Double numberTwo) {
		return (numberOne) / (numberTwo);
	}

	public Double average(Double numberOne, Double numberTwo) {
		return ((numberOne) + (numberTwo)) / 2;
	}

	public Double squareRoot(Double number) {
		return Math.sqrt((number));
	}
}
