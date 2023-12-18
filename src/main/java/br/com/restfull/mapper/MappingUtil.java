package br.com.restfull.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.restfull.model.Book;
import br.com.restfull.model.Person;
import br.com.restfull.vo.v1.BookValueObject;
import br.com.restfull.vo.v1.PersonValueObject;

public class MappingUtil {

	public static ModelMapper mapper = new ModelMapper();
	
	public static <O, D> D parseObject(O origin, Class<D> destination) {
		return mapper.map(origin, destination);
	}

	public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
		List<D> destinationObjects = new ArrayList<D>();
		for (O originItem : origin) {
			destinationObjects.add(mapper.map(originItem, destination));
		}
		return destinationObjects;
	}
	
	static {
		mapper.createTypeMap(Person.class, PersonValueObject.class).addMapping(Person::getId,
				PersonValueObject::setKey);
		mapper.createTypeMap(PersonValueObject.class, Person.class).addMapping(PersonValueObject::getKey,
				Person::setId);
		mapper.createTypeMap(Book.class, BookValueObject.class).addMapping(Book::getId,
				BookValueObject::setKey);
		mapper.createTypeMap(BookValueObject.class, Book.class).addMapping(BookValueObject::getKey,
				Book::setId);
	}
}
