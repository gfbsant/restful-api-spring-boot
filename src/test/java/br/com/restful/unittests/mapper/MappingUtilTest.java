package br.com.restful.unittests.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.restful.mapper.MappingUtil;
import br.com.restful.model.Book;
import br.com.restful.model.Person;
import br.com.restful.unittests.mapper.mocks.MockBook;
import br.com.restful.unittests.mapper.mocks.MockPerson;
import br.com.restful.vo.v1.BookValueObject;
import br.com.restful.vo.v1.PersonValueObject;

public class MappingUtilTest {

	    MockPerson inputPersonObject;
	    MockBook inputBookObject;

	    @BeforeEach
	    public void setUp() {
	    	inputPersonObject = new MockPerson();
	    	inputBookObject = new MockBook();
	    }

	    @Test
	    public void parseEntityToVOTest() throws ParseException {
	        PersonValueObject personOutput = MappingUtil.parseObject(inputPersonObject.mockEntity(), PersonValueObject.class);
	        assertEquals(Long.valueOf(0L), personOutput.getKey());
	        assertEquals("First Name Test 0", personOutput.getFirstName());
	        assertEquals("Last Name Test 0", personOutput.getLastName());
	        assertEquals("Address Test 0", personOutput.getAddress());
	        assertEquals("Male", personOutput.getGender());
	        
	        BookValueObject bookOutput = MappingUtil.parseObject(inputBookObject.mockEntity(), BookValueObject.class);
	        assertEquals(Long.valueOf(0L), bookOutput.getKey());
	        assertEquals("Author Test 0", bookOutput.getAuthor());
	        assertEquals("Title Test 0", bookOutput.getTitle());
	        assertEquals(0d, bookOutput.getPrice());
	        assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-17"), bookOutput.getLaunchDate());
	    }

	    @Test
	    public void parseEntityListToVOListTest() throws ParseException {
	        List<PersonValueObject> personOutputList = MappingUtil.parseListObjects(inputPersonObject.mockEntityList(), PersonValueObject.class);
	        
	        PersonValueObject personOutputTwelve = personOutputList.get(12);
	        
	        assertEquals(Long.valueOf(12L), personOutputTwelve.getKey());
	        assertEquals("First Name Test 12", personOutputTwelve.getFirstName());
	        assertEquals("Last Name Test 12", personOutputTwelve.getLastName());
	        assertEquals("Address Test 12", personOutputTwelve.getAddress());
	        assertEquals("Male", personOutputTwelve.getGender());
	        
	        List<BookValueObject> bookOutputList = MappingUtil.parseListObjects(inputBookObject.mockEntityList(), BookValueObject.class);
	        
	        BookValueObject bookOutputTwelve = bookOutputList.get(12);
	        
	        assertEquals(Long.valueOf(12), bookOutputTwelve.getKey());
	        assertEquals("Author Test 12", bookOutputTwelve.getAuthor());
	        assertEquals("Title Test 12", bookOutputTwelve.getTitle());
	        assertEquals(12d, bookOutputTwelve.getPrice());
	        assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-17"), bookOutputTwelve.getLaunchDate());
	    }

	    @Test
	    public void parseVOToEntityTest() throws ParseException {
	        Person personOutput = MappingUtil.parseObject(inputPersonObject.mockVO(), Person.class);
	        assertEquals(Long.valueOf(0L), personOutput.getId());
	        assertEquals("First Name Test 0", personOutput.getFirstName());
	        assertEquals("Last Name Test 0", personOutput.getLastName());
	        assertEquals("Address Test 0", personOutput.getAddress());
	        assertEquals("Male", personOutput.getGender());
	        
	        Book bookOutput = MappingUtil.parseObject(inputBookObject.mockVO(), Book.class);
	        assertEquals(Long.valueOf(0L), bookOutput.getId());
	        assertEquals("Author Test 0", bookOutput.getAuthor());
	        assertEquals("Title Test 0", bookOutput.getTitle());
	        assertEquals(0d, bookOutput.getPrice());
	        assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-17"), bookOutput.getLaunchDate());
	    }

	    @Test
	    public void parserVOListToEntityListTest() throws ParseException {
	        List<Person> personOutputList = MappingUtil.parseListObjects(inputPersonObject.mockVOList(), Person.class);
	        
	        Person personOutputTwelve = personOutputList.get(12);
	        
	        assertEquals(Long.valueOf(12L), personOutputTwelve.getId());
	        assertEquals("First Name Test 12", personOutputTwelve.getFirstName());
	        assertEquals("Last Name Test 12", personOutputTwelve.getLastName());
	        assertEquals("Address Test 12", personOutputTwelve.getAddress());
	        assertEquals("Male", personOutputTwelve.getGender());
	        
	        List<Book> outputList = MappingUtil.parseListObjects(inputBookObject.mockVOList(), Book.class);
	        
	        Book bookOutputTwelve = outputList.get(12);
	        
	        assertEquals(Long.valueOf(12L), bookOutputTwelve.getId());
	        assertEquals("Author Test 12", bookOutputTwelve.getAuthor());
	        assertEquals("Title Test 12", bookOutputTwelve.getTitle());
	        assertEquals(12d, bookOutputTwelve.getPrice());
	        assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-17"), bookOutputTwelve.getLaunchDate());
	    }
	
}
