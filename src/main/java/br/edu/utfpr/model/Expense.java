package br.edu.utfpr.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.edu.utfpr.util.Format;

@Entity
@Table(name = "expenses")
public class Expense {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exp_id")
	private Long id;
	@Column(name = "exp_description", nullable = false)
	private String description;
	@Column(name = "exp_value_pay", nullable = false)
	private Double valuePay;
	@Column(name = "exp_date_pay", nullable = false)
	@Temporal(value = TemporalType.DATE)
	private Date datePay;
	@Column(name = "exp_paid", nullable = false)
	private String paid;
	@Transient
	private Map<String, String> errors;

	public Expense() {
		this.id = null;
		this.description = "";
		this.valuePay = 0D;
		this.datePay = null;
		this.paid = "F";
	}

	public Expense(Long id, String description, double valuePay, Date datePay, String paid) {
		this.setId(id);
		this.setDescription(description);
		this.setValuePay(valuePay);
		this.setDatePay(datePay);
		this.setPaid(paid);
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getValuePay() {
		return valuePay;
	}

	public void setValuePay(Double valuePay) {
		this.valuePay = valuePay;
	}

	public Date getDatePay() {
		return datePay;
	}

	public void setDatePay(Date datePay) {
		this.datePay = datePay;
	}

	public String getPaid() {
		return paid;
	}

	public void setPaid(String paid) {
		this.paid = paid;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public boolean isValid() {
		validation();
		return this.errors.isEmpty();
	}

	public void validation() {
		this.errors = new HashMap<String, String>();
		
		if (this.description.length() < 3 || this.description == null) {
			this.errors.put("description", "Descrição inválida.");
		}
		
		if (this.valuePay <= 0 || this.valuePay == null) {
			this.errors.put("valuePay", "Valor a pagar não informado.");
		}
		
		if (this.datePay == null) {
			this.errors.put("datePay", "Data Inválida.");
		}
	}
}
