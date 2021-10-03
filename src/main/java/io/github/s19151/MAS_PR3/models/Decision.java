package io.github.s19151.MAS_PR3.models;

import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "decisions")
public class Decision {
	private long id;
	private LocalDate issueDate;
	private String decisionText;
	
	//connections
	private OrderComplaint orderComplaint = null;
	private Consultant consultant = null;
	
	public Decision() {}
	
	public Decision(LocalDate issueDate, String decisionText) {
		this.issueDate = issueDate;
		this.decisionText = decisionText;
	}
	
	//functional methods
	
	//connections
	
	@ManyToOne
	public OrderComplaint getOrderComplaint() {
		return orderComplaint;
	}
	
	public void setOrderComplaint(OrderComplaint orderComplaint) {
		if(this.orderComplaint != null && !this.orderComplaint.equals(orderComplaint)) {
			this.orderComplaint.removeDecision(this);
		}
		
		this.orderComplaint = orderComplaint;
		orderComplaint.addDecision(this);
	}
	
	public void removeOrderComplaint(OrderComplaint orderComplaint) {
		if(this.orderComplaint != null && this.orderComplaint.equals(orderComplaint)) {
			this.orderComplaint = null;
			orderComplaint.removeDecision(this);
		}
	}
	
	@ManyToOne
	public Consultant getConsultant() {
		return consultant;
	}
	
	public void setConsultant(Consultant consultant) {
		if(this.consultant != null && !this.consultant.equals(consultant)) {
			this.consultant.removeDecision(this);
		}
		
		this.consultant = consultant;
		consultant.addDecision(this);
	}
	
	public void removeConsultant(Consultant consultant) {
		if(this.consultant != null && this.consultant.equals(consultant)) {
			this.consultant = null;
			consultant.removeDecision(this);
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
	public LocalDate getIssueDate() {
		return issueDate;
	}
	
	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}
	
	@Basic
	public String getDecisionText() {
		return decisionText;
	}
	
	public void setDecisionText(String decisionText) {
		this.decisionText = decisionText;
	}
	
	@Override
	public String toString() {
		return String.format(
				"%s: %d %s %s",
				getClass().getName(),
				getId(),
				getIssueDate().toString(),
				getDecisionText()
			);
	}
}
