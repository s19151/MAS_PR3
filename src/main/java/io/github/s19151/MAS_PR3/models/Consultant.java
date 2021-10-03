package io.github.s19151.MAS_PR3.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "consultants")
public class Consultant extends Worker {
	//connections
	private Set<Decision> decisions = new HashSet();
	
	public Consultant() {};
	
	public Consultant(Person person, LocalDate employmentDate, double ratePerHour) {
		super(person, employmentDate, ratePerHour);
	}
	
	public Consultant(Person person, LocalDate employmentDate) {
		super(person, employmentDate);
	}
	
	//functional methods
	
	//connections
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<Decision> getDecisions() {
		return decisions;
	}
	
	public void setDecisions(Set<Decision> orderComplaints) {
		this.decisions = orderComplaints;
	}
	
	public void addDecision(Decision decision) {
		if(!decisions.contains(decision)) {
			decisions.add(decision);
			decision.setConsultant(this);
		}
	}

	public void removeDecision(Decision decision) {
		if(decisions.contains(decision)) {
			decisions.remove(decision);
			decision.removeConsultant(this);
		}
	}
	
	//getters and setters
}
