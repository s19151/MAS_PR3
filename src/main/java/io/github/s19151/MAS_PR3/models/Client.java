package io.github.s19151.MAS_PR3.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity()
@Table(name = "clients")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Client {
	private long id;
	private List<String> phoneNumbers;
	private List<String> emails;
	private String address;
	
	//connections
	private Set<Order> orders = new HashSet();
	
	public Client() {}
	
	public Client (List<String> phoneNumbers, List<String> emails, String address) {
		this.phoneNumbers = phoneNumbers;
		this.emails = emails;
		this.address = address;
	}
	
	//functional methods
	
	//connections
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<Order> getOrders() {
		return orders;
	}
	
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
	public void addOrder(Order order) {
		if(!orders.contains(order)) {
			orders.add(order);
			order.setClient(this);
		}
	}

	public void removeOrder(Order order) {
		if(orders.contains(order)) {
			orders.remove(order);
			order.removeClient(this);
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
	
	@Override
	public String toString() {
		return String.format(
				"%s: %d %s",
				getClass().getName(),
				getId(),
				address
			);
	}
}
