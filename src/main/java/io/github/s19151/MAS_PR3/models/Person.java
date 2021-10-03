package io.github.s19151.MAS_PR3.models;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Person {
	private String firstname;
	private String lastname;
	private LocalDate birthdate;
	
	public Person() {}
	
	public Person(String firstname, String lastname, LocalDate birthdate) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthdate = birthdate;
	}
	
	@Basic
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	@Basic
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	@Basic
	public LocalDate getBirthdate() {
		return birthdate;
	}
	
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	
	@Transient
	public int getAge() {
		return Period.between(getBirthdate(), LocalDate.now()).getYears();
	}
	
	@Override
	public String toString() {
		return String.format(
				"%s %s %s",
				getFirstname(),
				getLastname(),
				getBirthdate().toString()
			);
	}
}
