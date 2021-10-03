package io.github.s19151.MAS_PR3.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "supplyComplaints")
public class SupplyComplaint extends Complaint {
	//connections
	private Supply supply = null;
	private Manager manager = null;
	
	//functional methods
	
	//connections
	
	@ManyToOne
	public Supply getSupply() {
		return supply;
	}
	
	public void setSupply(Supply supply) {
		if(this.supply != null && !this.supply.equals(supply)) {
			this.supply.removeSupplyComplaint(this);
		}
		
		this.supply = supply;
		supply.addSupplyComplaint(this);
	}
	
	public void removeSupply(Supply supply) {
		if(this.supply != null && this.supply.equals(supply)) {
			this.supply = null;
			supply.removeSupplyComplaint(this);
		}
	}
	
	@ManyToOne
	public Manager getManager() {
		return manager;
	}
	
	public void setManager(Manager manager) {
		if(this.manager != null && !this.manager.equals(manager)) {
			this.manager.removeSupplyComplaint(this);
		}
		
		this.manager = manager;
		manager.addSupplyComplaint(this);
	}
	
	public void removeManager(Manager manager) {
		if(this.manager != null && this.manager.equals(manager)) {
			this.manager = null;
			manager.removeSupplyComplaint(this);
		}
	}
	
	//getters and setters
	
}
