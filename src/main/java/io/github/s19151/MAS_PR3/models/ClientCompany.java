package io.github.s19151.MAS_PR3.models;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "clientCompanies")
public class ClientCompany extends Client {
	private String companyName;
	private String NIP;
	private String REGON;
	
	public ClientCompany() {}
	
	public ClientCompany(List<String> phoneNumbers, List<String> emails, String address, String companyName, String NIP, String REGON) {
		super(phoneNumbers, emails, address);
		
		this.companyName = companyName;
		this.NIP = NIP;
		this.REGON = REGON;
	}
	
	//getters and setters
	
	@Basic
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@Basic
	public String getNIP() {
		return NIP;
	}
	
	public void setNIP(String NIP) {
		this.NIP = NIP;
	}
	
	@Basic
	public String getREGON() {
		return REGON;
	}
	
	public void setREGON(String REGON) {
		this.REGON = REGON;
	}
	
	@Override
	public String toString() {
		return String.format(
				"%s %s %s %s",
				super.toString(),
				getCompanyName(),
				getNIP(),
				getREGON()
			);
	}
}
