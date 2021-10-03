package io.github.s19151.MAS_PR3.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product {
	private long id;
	private String name;
	private double price;
	private String description;
	private ProductType productType;
	private String code;
	
	//connections
	private Set<OrderProduct> orderProducts = new HashSet();
	private Set<SupplyProduct> supplyProducts = new HashSet();
	
	Product() {}
	
	Product(String name, double price, String description, ProductType productType, String code) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.productType = productType;
		this.code = code;
	}
	
	//functional methods
	
	//connections
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<OrderProduct> getOrderProducts() {
		return orderProducts;
	}
	
	public void setOrderProducts(Set<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}
	
	public void addOrderProduct(OrderProduct orderProduct) {
		if(!orderProducts.contains(orderProduct)) {
			orderProducts.add(orderProduct);
			orderProduct.setProduct(this);
		}
	}

	public void removeOrderProduct(OrderProduct orderProduct) {
		if(orderProducts.contains(orderProduct)) {
			orderProducts.remove(orderProduct);
			orderProduct.removeProduct(this);
		}
	}
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<SupplyProduct> getSupplyProducts() {
		return supplyProducts;
	}
	
	public void setSupplyProducts(Set<SupplyProduct> supplyProducts) {
		this.supplyProducts = supplyProducts;
	}
	
	public void addSupplyProduct(SupplyProduct supplyProduct) {
		if(!supplyProducts.contains(supplyProduct)) {
			supplyProducts.add(supplyProduct);
			supplyProduct.setProduct(this);
		}
	}

	public void removeSupplyProduct(SupplyProduct supplyProduct) {
		if(supplyProducts.contains(supplyProduct)) {
			supplyProducts.remove(supplyProduct);
			supplyProduct.removeProduct(this);
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
	
	@Basic
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Basic
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Enumerated
	public ProductType getProductType() {
		return productType;
	}
	
	public void setProductType(ProductType productType) {
		this.productType = productType;
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
				"%s: %d %s %f %s %s %s",
				getClass().getName(),
				getId(),
				getName(),
				getPrice(),
				getDescription(),
				getProductType(),
				getCode()
			);
	}
}

