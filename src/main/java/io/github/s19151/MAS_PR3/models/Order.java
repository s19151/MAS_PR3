package io.github.s19151.MAS_PR3.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Orders")
public class Order {
	private long id;
	private LocalDate orderDate;
	private LocalDate orderCloseDate;
	private OrderState orderState;
	private long fulfillmentTime;
	private Opinion opinion;
	
	//connections
	private Client client = null;
	private Warehouseman warehouseman = null;
	private Set<OrderProduct> orderProducts = new HashSet();
	private Set<OrderComplaint> orderComplaints = new HashSet();
	
	public Order() {}
	
	public Order(LocalDate orderDate, LocalDate orderCloseDate, OrderState orderState, long fulfillmentTime, Opinion opinion) {
		this.orderDate = orderDate;
		this.orderCloseDate = orderCloseDate;
		this.orderState = orderState;
		this.fulfillmentTime = fulfillmentTime;
	}
	
	public Order(LocalDate orderDate, LocalDate orderCloseDate, OrderState orderState, long fulfillmentTime) {
		this.orderDate = orderDate;
		this.orderCloseDate = orderCloseDate;
		this.orderState = orderState;
		this.fulfillmentTime = fulfillmentTime;
	}
	
	public Order(LocalDate orderDate, LocalDate orderCloseDate) {
		this.orderDate = orderDate;
		this.orderCloseDate = orderCloseDate;
		this.orderState = OrderState.ORDERED;
	}
	
	//functional methods
	
	//connections
	
	@ManyToOne
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		if(this.client != null && !this.client.equals(client)) {
			this.client.removeOrder(this);
		}
		this.client = client;
		client.addOrder(this);
	}
	
	public void removeClient(Client client) {
		if(this.client != null && this.client.equals(client)) {
			this.client = null;
			client.removeOrder(this);
		}
	}
	
	@ManyToOne
	public Warehouseman getWarehouseman() {
		return warehouseman;
	}
	
	public void setWarehouseman(Warehouseman warehouseman) {
		if(this.warehouseman != null && !this.warehouseman.equals(warehouseman)) {
			this.warehouseman.removeOrder(this);
		}
		
		this.warehouseman = warehouseman;
		warehouseman.addOrder(this);	
	}
	
	public void removeWarehouseman(Warehouseman warehouseman) {
		if(this.warehouseman != null && this.warehouseman.equals(warehouseman)) {
			this.warehouseman = null;
			warehouseman.removeOrder(this);
		}
	}
	
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
			orderProduct.setOrder(this);
		}
	}

	public void removeOrderProduct(OrderProduct orderProduct) {
		if(orderProducts.contains(orderProduct)) {
			orderProducts.remove(orderProduct);
			orderProduct.removeOrder(this);
		}
	}
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<OrderComplaint> getOrderComplaints() {
		return orderComplaints;
	}
	
	public void setOrderComplaints(Set<OrderComplaint> orderComplaints) {
		this.orderComplaints = orderComplaints;
	}
	
	public void addOrderComplaint(OrderComplaint orderComplaint) {
		if(!orderComplaints.contains(orderComplaint)) {
			orderComplaints.add(orderComplaint);
			orderComplaint.setOrder(this);
		}
	}

	public void removeOrderComplaint(OrderComplaint orderComplaint) {
		if(orderComplaints.contains(orderComplaint)) {
			orderComplaints.remove(orderComplaint);
			orderComplaint.removeOrder(this);
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
	public LocalDate getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	
	@Basic
	public LocalDate getOrderCloseDate() {
		return orderCloseDate;
	}
	
	public void setOrderCloseDate(LocalDate orderCloseDate) {
		this.orderCloseDate = orderCloseDate;
	}
	
	@Enumerated
	public OrderState getOrderState() {
		return orderState;
	}
	
	public void setOrderState(OrderState orderState) {
		if(orderState.equals(OrderState.FULFILLED) || orderState.equals(OrderState.CANCELED))
			setOrderCloseDate(LocalDate.now());
		
		this.orderState = orderState;
	}
	
	@Basic
	public long getFulfillmentTime() {
		return fulfillmentTime;
	}
	
	public void setFulfillmentTime(long fulfillmentTime) {
		this.fulfillmentTime = fulfillmentTime;
	}
	
	@Embedded
	public Opinion getOpinion() {
		return opinion;
	}
	
	public void setOpinion(Opinion opinion) {
		this.opinion = opinion;
	}
	
	@Transient
	public double getPrice() {
		double res = 0.;
		
		for(OrderProduct op : orderProducts)
			res += op.getPrice();
		
		return res;
	}
	
	@Override
	public String toString() {
		return String.format(
				"%s: %d %s %s %s %s",
				getClass().getName(),
				getId(),
				getOrderDate().toString(),
				getOrderCloseDate().toString(),
				getOrderState(),
				(getOpinion() != null ? getOpinion().toString() : "")
			);
	}
}