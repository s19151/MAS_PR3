package io.github.s19151.MAS_PR3.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "suppliers")
public class Supplier {
	private long id;
	private String name;
	private List<String> phoneNumbers;
	private List<String> emails;
	private String address;
	private String bankAccount;
	private LocalDate coopCommencementDate;
	
	//connections
	private Set<Supply> supplies = new HashSet();
	
	public Supplier() {}
	
	public Supplier(String name, List<String> phoneNumbers, List<String> emails, String address, String bankAccount, LocalDate coopCommencementDate) {
		this.name = name;
		this.phoneNumbers = phoneNumbers;
		this.emails = emails;
		this.address = address;
		this.bankAccount = bankAccount;
		this.coopCommencementDate = coopCommencementDate;
	}
	
	//functional methods
	
	//connections
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<Supply> getSupplies() {
		return supplies;
	}
	
	public void setSupplies(Set<Supply> supplies) {
		this.supplies = supplies;
	}
	
	public void addSupply(Supply supply) {
		if(!supplies.contains(supply)) {
			supplies.add(supply);
			supply.setSupplier(this);
		}
	}

	public void removeSupply(Supply supply) {
		if(supplies.contains(supply)) {
			supplies.remove(supply);
			supply.removeSupplier(this);
		}
	}
	
	//getters and setters
	
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
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@ElementCollection
	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}
	
	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	
	@ElementCollection
	public List<String> getEmails() {
		return emails;
	}
	
	public void setEmails(List<String> emails) {
		this.emails = emails;
	}
	
	@Basic
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Basic
	public String getBankAccount() {
		return bankAccount;
	}
	
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	@Basic
	public LocalDate getCoopCommencementDate() {
		return coopCommencementDate;
	}
	
	public void setCoopCommencementDate(LocalDate coopCommencementDate) {
		this.coopCommencementDate = coopCommencementDate;
	}
	
	@Override
	public String toString() {
		return String.format(
				"%s: %d %s %s %s %s %s %s",
				getClass().getName(),
				getId(),
				getName(),
				getPhoneNumbers().toString(),
				getEmails().toString(),
				getAddress(),
				getBankAccount(),
				getCoopCommencementDate()
			);
	}
}
