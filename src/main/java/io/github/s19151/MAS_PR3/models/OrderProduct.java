package io.github.s19151.MAS_PR3.models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "orderProducts")
public class OrderProduct {
	private long id;
	private int amount;
	private String code;
	
	//connections
	private Order order = null;
	private Product product = null;
	
	public OrderProduct() {}
	
	public OrderProduct(int amount, String code) {
		this.amount = amount;
		this.code = code;
	}
	
	//functional methods
	
	//connections
	@ManyToOne
	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		if(this.order != null && !this.order.equals(order)) {
			this.order.removeOrderProduct(this);
		}
		
		this.order = order;
		order.addOrderProduct(this);
	}
	
	public void removeOrder(Order order) {
		if(this.order != null && this.order.equals(order)) {
			this.order = null;
			order.removeOrderProduct(this);
		}
	}
	
	@ManyToOne
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		if(this.product != null && !this.product.equals(product)) {
			this.product.removeOrderProduct(this);
		}
		
		this.product = product;
		product.addOrderProduct(this);
	}
	
	public void removeProduct(Product product) {
		if(this.product != null && this.product.equals(product)) {
			this.product = null;
			product.removeOrderProduct(this);
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
	
	@Transient
	public double getPrice() {
		return product.getPrice()*amount;
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
