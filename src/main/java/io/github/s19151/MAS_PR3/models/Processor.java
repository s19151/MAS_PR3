package io.github.s19151.MAS_PR3.models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "processors")
public class Processor extends Product{
	private int cores;
	private int threads;
	private double clockSpeed;
	
	public Processor() {}

	public Processor(String name, double price, String description, ProductType productType, String code, int cores, int threads, double clockSpeed) {
		super(name, price, description, productType, code);
		
		this.cores = cores;
		this.threads = threads;
		this.clockSpeed = clockSpeed;
	}
	
	@Basic
	public int getCores() {
		return cores;
	}
	
	public void setCores(int cores) {
		this.cores = cores;
	}
	
	@Basic
	public int getThreads() {
		return threads;
	}
	
	public void setThreads(int threads) {
		this.threads = threads;
	}
	
	@Basic
	public double getClockSpeed() {
		return clockSpeed;
	}
	
	public void setClockSpeed(double clockSpeed) {
		this.clockSpeed = clockSpeed;
	}
	
	@Override
	public String toString() {
		return String.format(
				"%s %d %d %f",
				super.toString(),
				getCores(),
				getThreads(),
				getClockSpeed()
			);
	}
}
