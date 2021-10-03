package io.github.s19151.MAS_PR3.models;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "clientPeople")
public class ClientPerson extends Client{	
	private Person person;
	
	public ClientPerson () {}
	
	public ClientPerson (List<String> phoneNumbers, List<String> emails, String address, Person person) {
		super(phoneNumbers, emails, address);
		
		this.person = person;
	}
	
	@Embedded
	public Person getPerson() {
		return person;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
	
	@Transient
	public String getFirstname() {
		return person.getFirstname();
	}
	
	public void setFirstname(String firstname) {
		person.setFirstname(firstname);
	}
	
	@Transient
	public String getLastname() {
		return person.getLastname();
	}
	
	public void setLastname(String lastname) {
		person.setLastname(lastname);
	}
	
	@Transient
	public LocalDate getBirthdate() {
		return person.getBirthdate();
	}
	
	public void setBirthdate(LocalDate birthdate) {
		person.setBirthdate(birthdate);
	}
	
	@Transient
	public int getAge() {
		return Period.between(getBirthdate(), LocalDate.now()).getYears();
	}
	
	@Override
	public String toString() {
		return String.format(
				"%s %s",
				super.toString(),
				person.toString()
			);
	}
}
