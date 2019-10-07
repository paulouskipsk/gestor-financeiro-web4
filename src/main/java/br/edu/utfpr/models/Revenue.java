package br.edu.utfpr.models;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

public class Revenue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rev_id", nullable=false)
	private long revId;
	
	@Column(name="rev_description", nullable=false)
	private String revDescription;
	
	@Column(name="rev_value_receiveble", nullable=false)
	private double revValueReceiveble;
	
	@Column(name="rev_date_receiveble", nullable=false)
	private Date revDateReceiveble;
	
	@Column(name="rev_receivebled", nullable=false)
	private char receivebled;
	
	@Transient
	private Map<String, String> errors;

	public Revenue(long revId, String revDescription, double revValueReceiveble, Date revDateReceiveble, char receivebled) {
		this.revId = revId;
		this.revDescription = revDescription;
		this.revValueReceiveble = revValueReceiveble;
		this.revDateReceiveble = revDateReceiveble;
		this.receivebled = receivebled;
	}

	public Revenue() {
		this.errors = new HashMap<String, String>();
	}

	public long getRevId() {
		return revId;
	}

	public void setRevId(long revId) {
		this.revId = revId;
	}

	public String getRevDescription() {
		return revDescription;
	}

	public void setRevDescription(String revDescription) {
		this.revDescription = revDescription;
	}

	public double getRevValueReceiveble() {
		return revValueReceiveble;
	}

	public void setRevValueReceiveble(double revValueReceiveble) {
		this.revValueReceiveble = revValueReceiveble;
	}

	public Date getRevDateReceiveble() {
		return revDateReceiveble;
	}

	public void setRevDateReceiveble(Date revDateReceiveble) {
		this.revDateReceiveble = revDateReceiveble;
	}

	public char getReceivebled() {
		return receivebled;
	}

	public void setReceivebled(char receivebled) {
		this.receivebled = receivebled;
	}
	
	public boolean isValid() {
		return this.errors.isEmpty();
	}
	
	private void validation() {
		this.errors = new HashMap<String, String>();
	}
}
