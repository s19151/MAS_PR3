package io.github.s19151.MAS_PR3.models;

import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "complaints")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Complaint {
	private long id;
	private LocalDate complaintDate;
	private String complaintReason;
	private String additionalInformation;
	private ComplaintState complaintState;
	
	public Complaint () {}
	
	public Complaint (LocalDate complaintDate, String complaintReason, String additionalInformation, ComplaintState complaintState) {
		this.complaintDate = complaintDate;
		this.complaintReason = complaintReason;
		this.additionalInformation = additionalInformation;
		this.complaintState = complaintState;
	}
	

	public Complaint (LocalDate complaintDate, String complaintReason, ComplaintState complaintState) {
		this.complaintDate = complaintDate;
		this.complaintReason = complaintReason;
		this.complaintState = complaintState;
	}
	
	public Complaint (LocalDate complaintDate, String complaintReason, String additionalInformation) {
		this.complaintDate = complaintDate;
		this.complaintReason = complaintReason;
		this.additionalInformation = additionalInformation;
		this.complaintState = ComplaintState.Opened;
	}
	
	public Complaint (LocalDate complaintDate, String complaintReason) {
		this.complaintDate = complaintDate;
		this.complaintReason = complaintReason;
		this.complaintState = ComplaintState.Closed;
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
	
	public LocalDate getComplaintDate() {
		return complaintDate;
	}
	
	public void setComplaintDate(LocalDate complaintDate) {
		this.complaintDate = complaintDate;
	}
	
	@Basic
	public String getComplaintReason() {
		return complaintReason;
	}
	
	public void setComplaintReason(String complaintReason) {
		this.complaintReason = complaintReason;
	}
	
	@Basic
	public String getAdditionalInformation() {
		return (additionalInformation == null || additionalInformation.isEmpty() ? "" : additionalInformation);
	}
	
	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}
	
	@Enumerated
	public ComplaintState getComplaintState() {
		return complaintState;
	}
	
	public void setComplaintState(ComplaintState complaintState) {
		this.complaintState = complaintState;
	}
	
	@Override
	public String toString() {
		return String.format(
				"%s: %d %s %s %s %s",
				getClass().getName(),
				getId(),
				getComplaintDate().toString(),
				getComplaintReason(),
				getAdditionalInformation(),
				getComplaintState()
			);
	}

}

enum ComplaintState {
	Opened, Closed, Canceled;
}
