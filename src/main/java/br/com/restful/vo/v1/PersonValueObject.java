package br.com.restful.vo.v1;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;



@JsonPropertyOrder({ "id", "firstName", "lastName", "address", "gender" })
public class PersonValueObject extends RepresentationModel<PersonValueObject> implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private Long key;

	private String firstName;

	private String lastName;

	private String address;

	private String gender;

	public PersonValueObject() {
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(key);
		return result;
	}

	@Override
	public boolean equals(@Nullable Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (obj != null && getClass() != obj.getClass())
			return false;
		PersonValueObject other = (PersonValueObject) obj;
		return other != null && Objects.equals(key, other.key);
	}


}
