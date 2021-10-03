package io.github.s19151.MAS_PR3.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orderComplaints")
public class OrderComplaint extends Complaint {
	//connections
	private Order order = null;
	private Set<Decision> decisions = new HashSet();
	
	//functional methods
	
	//connections
	
	@ManyToOne
	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		if(this.order != null && !this.order.equals(order)) {
			this.order.removeOrderComplaint(this);
		}
		
		this.order = order;
		order.addOrderComplaint(this);
	}
	
	public void removeOrder(Order supply) {
		if(this.order != null && this.order.equals(order)) {
			this.order = null;
			supply.removeOrderComplaint(this);
		}
	}
	
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
			decision.setOrderComplaint(this);
		}
	}

	public void removeDecision(Decision decision) {
		if(decisions.contains(decision)) {
			decisions.remove(decision);
			decision.removeOrderComplaint(this);
		}
	}
	
	//getters and setters
		
}
