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
@Table(name = "managers")
public class Manager extends Worker {
	//connections
	private Set<SupplyComplaint> supplyComplaints = new HashSet();
	
	public Manager() {};
	
	public Manager(Person person, LocalDate employmentDate, double ratePerHour) {
		super(person, employmentDate, ratePerHour);
	}
	
	public Manager(Person person, LocalDate employmentDate) {
		super(person, employmentDate);
	}
	
	//functional methods
	
	//connections
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<SupplyComplaint> getSupplyComplaints() {
		return supplyComplaints;
	}
	
	public void setSupplyComplaints(Set<SupplyComplaint> supplyComplaints) {
		this.supplyComplaints = supplyComplaints;
	}
	
	public void addSupplyComplaint(SupplyComplaint supplyComplaint) {
		if(!supplyComplaints.contains(supplyComplaint)) {
			supplyComplaints.add(supplyComplaint);
			supplyComplaint.setManager(this);
		}
	}

	public void removeSupplyComplaint(SupplyComplaint supplyComplaint) {
		if(supplyComplaints.contains(supplyComplaint)) {
			supplyComplaints.remove(supplyComplaint);
			supplyComplaint.removeManager(this);
		}
	}
	
	//getters and setters
}
