package io.github.s19151.MAS_PR3.models;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Basic;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "workers")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Worker {
	private long id;
	private LocalDate employmentDate;
	private double ratePerHour;
	private Person person;
	
	private static double minRatePerHour = 15;
	
	public Worker() {};
	
	public Worker(Person person, LocalDate employmentDate, double ratePerHour) {
		this.person = person;
		this.employmentDate = employmentDate;
		this.ratePerHour = ratePerHour;
	}
	
	public Worker(Person person, LocalDate employmentDate) {
		this.person = person;
		this.employmentDate = employmentDate;
		this.ratePerHour = minRatePerHour;
	}
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	public long getId() {
		return id;
	}
	
	private void setId(long id) {
		this.id = id;
	}
	
	@Basic
	public LocalDate getEmploymentDate() {
		return employmentDate;
	}
	
	public void setEmploymentDate(LocalDate employmentDate) {
		this.employmentDate = employmentDate;
	}
	
	@Basic
	public double getRatePerHour() {
		return ratePerHour;
	}
	
	public void setRatePerHour(double ratePerHour) throws Exception {
		if(ratePerHour >= minRatePerHour)
			this.ratePerHour = ratePerHour;
		else
			throw new Exception("too small");
	}
	
	public static double getMinRatePerHour() throws Exception {
		return minRatePerHour;
	}
	
	public static void setMinRatePerHour(double ratePerHour) {
		minRatePerHour = ratePerHour;
	}
	
	@Transient
	public int getTimeInCompany() {
		return Period.between(getEmploymentDate(), LocalDate.now()).getYears();
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
				"%s: %d %s %s %f %d",
				getClass().getName(),
				getId(),
				getPerson().toString(),
				getEmploymentDate().toString(),
				getRatePerHour(),
				getTimeInCompany()
			);
	}
}
