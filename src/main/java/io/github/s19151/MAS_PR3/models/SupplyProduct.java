package io.github.s19151.MAS_PR3.models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "supplyProducts")
public class SupplyProduct {
	private long id;
	private int amount;
	private double price;
	private String code;
	
	//connections
	private Supply supply = null;
	private Product product = null;
	
	SupplyProduct() {}
	
	SupplyProduct(int amount, double price, String code) {
		this.amount = amount;
		this.price = price;
		this.code = code;
	}
	
	//functional methods
	
	//connections
	
	@ManyToOne
	public Supply getSupply() {
		return supply;
	}
	
	public void setSupply(Supply supply) {
		if(this.supply != null && !this.supply.equals(supply)) {
			this.supply.removeSupplyProduct(this);
		}
		
		this.supply = supply;
		supply.addSupplyProduct(this);
	}
	
	public void removeSupply(Supply supply) {
		if(this.supply != null && this.supply.equals(supply)) {
			this.supply = null;
			supply.removeSupplyProduct(this);
		}
	}
	
	@ManyToOne
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		if(this.product != null && !this.product.equals(product)) {
			this.product.removeSupplyProduct(this);
		}
		
		this.product = product;
		product.addSupplyProduct(this);
	}
	
	public void removeProduct(Product product) {
		if(this.product != null && this.product.equals(product)) {
			this.product = null;
			product.removeSupplyProduct(this);
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
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@Basic
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Basic
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		return String.format(
				"%s: %d %d %f %s",
				getClass().getName(),
				getId(),
				getAmount(),
				getPrice(),
				getCode()
			);
	}
}
