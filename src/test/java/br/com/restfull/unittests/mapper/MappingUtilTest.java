package br.com.restfull.unittests.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.restfull.mapper.MappingUtil;
import br.com.restfull.model.Person;
import br.com.restfull.unittests.mapper.mocks.MockPerson;
import br.com.restfull.vo.v1.PersonValueObject;

public class MappingUtilTest {

	    
	    MockPerson inputObject;

	    @BeforeEach
	    public void setUp() {
	        inputObject = new MockPerson();
	    }

	    @Test
	    public void parseEntityToVOTest() {
	        PersonValueObject output = MappingUtil.parseObject(inputObject.mockEntity(), PersonValueObject.class);
	        assertEquals(Long.valueOf(0L), output.getKey());
	        assertEquals("First Name Test0", output.getFirstName());
	        assertEquals("Last Name Test0", output.getLastName());
	        assertEquals("Address Test0", output.getAddress());
	        assertEquals("Male", output.getGender());
	    }

	    @Test
	    public void parseEntityListToVOListTest() {
	        List<PersonValueObject> outputList = MappingUtil.parseListObjects(inputObject.mockEntityList(), PersonValueObject.class);
	        PersonValueObject outputZero = outputList.get(0);
	        
	        assertEquals(Long.valueOf(0L), outputZero.getKey());
	        assertEquals("First Name Test0", outputZero.getFirstName());
	        assertEquals("Last Name Test0", outputZero.getLastName());
	        assertEquals("Address Test0", outputZero.getAddress());
	        assertEquals("Male", outputZero.getGender());
	        
	        PersonValueObject outputSeven = outputList.get(7);
	        
	        assertEquals(Long.valueOf(7L), outputSeven.getKey());
	        assertEquals("First Name Test7", outputSeven.getFirstName());
	        assertEquals("Last Name Test7", outputSeven.getLastName());
	        assertEquals("Address Test7", outputSeven.getAddress());
	        assertEquals("Female", outputSeven.getGender());
	        
	        PersonValueObject outputTwelve = outputList.get(12);
	        
	        assertEquals(Long.valueOf(12L), outputTwelve.getKey());
	        assertEquals("First Name Test12", outputTwelve.getFirstName());
	        assertEquals("Last Name Test12", outputTwelve.getLastName());
	        assertEquals("Address Test12", outputTwelve.getAddress());
	        assertEquals("Male", outputTwelve.getGender());
	    }

	    @Test
	    public void parseVOToEntityTest() {
	        Person output = MappingUtil.parseObject(inputObject.mockVO(), Person.class);
	        assertEquals(Long.valueOf(0L), output.getId());
	        assertEquals("First Name Test0", output.getFirstName());
	        assertEquals("Last Name Test0", output.getLastName());
	        assertEquals("Address Test0", output.getAddress());
	        assertEquals("Male", output.getGender());
	    }

	    @Test
	    public void parserVOListToEntityListTest() {
	        List<Person> outputList = MappingUtil.parseListObjects(inputObject.mockVOList(), Person.class);
	        Person outputZero = outputList.get(0);
	        
	        assertEquals(Long.valueOf(0L), outputZero.getId());
	        assertEquals("First Name Test0", outputZero.getFirstName());
	        assertEquals("Last Name Test0", outputZero.getLastName());
	        assertEquals("Address Test0", outputZero.getAddress());
	        assertEquals("Male", outputZero.getGender());
	        
	        Person outputSeven = outputList.get(7);
	        
	        assertEquals(Long.valueOf(7L), outputSeven.getId());
	        assertEquals("First Name Test7", outputSeven.getFirstName());
	        assertEquals("Last Name Test7", outputSeven.getLastName());
	        assertEquals("Address Test7", outputSeven.getAddress());
	        assertEquals("Female", outputSeven.getGender());
	        
	        Person outputTwelve = outputList.get(12);
	        
	        assertEquals(Long.valueOf(12L), outputTwelve.getId());
	        assertEquals("First Name Test12", outputTwelve.getFirstName());
	        assertEquals("Last Name Test12", outputTwelve.getLastName());
	        assertEquals("Address Test12", outputTwelve.getAddress());
	        assertEquals("Male", outputTwelve.getGender());
	    }
	
}
