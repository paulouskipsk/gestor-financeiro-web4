package br.edu.utfpr.models;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

public class Expense {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="exp_id", nullable=false)
	private long expId;
	
	@Column(name="exp_description", nullable=false)
	private String expDescription;
	
	@Column(name="exp_value_pay", nullable=false)
	private double expValuePay;
	
	@Column(name="exp_date_pay", nullable=false)
	private Date expDatePay;
	
	@Column(name="exp_paid", nullable=false)
	private char expPaid;
	
	@Transient
	private Map<String, String> errors;

	public Expense(long expId, String expDescription, double expValuePay, Date expDatePay, char expPaid) {
		this.expId = expId;
		this.expDescription = expDescription;
		this.expValuePay = expValuePay;
		this.expDatePay = expDatePay;
		this.expPaid = expPaid;
	}

	public Expense() {}

	public long getExpId() {
		return expId;
	}

	public void setExpId(long expId) {
		this.expId = expId;
	}

	public String getExpDescription() {
		return expDescription;
	}

	public void setExpDescription(String expDescription) {
		this.expDescription = expDescription;
	}

	public double getExpValuePay() {
		return expValuePay;
	}

	public void setExpValuePay(double expValuePay) {
		this.expValuePay = expValuePay;
	}

	public Date getExpDatePay() {
		return expDatePay;
	}

	public void setExpDatePay(Date expDatePay) {
		this.expDatePay = expDatePay;
	}

	public char getExpPaid() {
		return expPaid;
	}

	public void setExpPaid(char expPaid) {
		this.expPaid = expPaid;
	}
	
	public boolean isValid() {
		validation();
		return this.errors.isEmpty();
	}
	
	public void validation() {
		this.errors = new HashMap<String, String>();
	}	

}
