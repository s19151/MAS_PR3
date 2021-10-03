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
@Table(name = "warehouseman")
public class Warehouseman extends Worker {
	//connections
	private Set<Order> orders = new HashSet();
	
	public Warehouseman() {};
	
	public Warehouseman(Person person, LocalDate employmentDate, double ratePerHour) {
		super(person, employmentDate, ratePerHour);
	}
	
	public Warehouseman(Person person, LocalDate employmentDate) {
		super(person, employmentDate);
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
			order.setWarehouseman(this);
		}
	}

	public void removeOrder(Order order) {
		if(orders.contains(order)) {
			orders.remove(order);
			order.removeWarehouseman(this);
		}
	}
	
	//getters and setters
}
