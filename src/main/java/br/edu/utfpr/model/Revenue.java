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

@Entity
@Table(name="revenues")
public class Revenue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rev_id", nullable=false)
	private Long id;
	@Column(name="rev_description", nullable=false)
	private String description;
	@Column(name="rev_value_receiveble", nullable=false)
	private Double valueReceiveble;
	@Column(name="rev_date_receiveble", nullable=false)
	@Temporal(value = TemporalType.DATE)
	private Date dateReceiveble;
	@Column(name="rev_receivebled", nullable=false)
	private String receivebled;
	@Transient
	private Map<String, String> errors;

	public Revenue(Long id, String revDescription, Double revValueReceiveble, Date date, String receivebled) {
		this.id = id;
		this.description = revDescription;
		this.valueReceiveble = revValueReceiveble;
		this.dateReceiveble = date;
		this.receivebled = receivebled;
	}

	public Revenue() {
		this.id = null;
		this.description = "";
		this.valueReceiveble = 0D;
		this.dateReceiveble = null;
		this.receivebled = "F";
		this.errors = new HashMap<String, String>();
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

	public Double getValueReceiveble() {
		return valueReceiveble;
	}

	public void setValueReceiveble(Double valueReceiveble) {
		this.valueReceiveble = valueReceiveble;
	}

	public Date getDateReceiveble() {
		return dateReceiveble;
	}

	public void setDateReceiveble(Date dateReceiveble) {
		this.dateReceiveble = dateReceiveble;
	}

	public String getReceivebled() {
		return receivebled;
	}

	public void setReceivebled(String receivebled) {
		this.receivebled = receivebled;
	}
	
	public boolean isValid() {
		this.validation();
		return this.errors.isEmpty();
	}
	
	private void validation() {
		this.errors = new HashMap<String, String>();
	}
}
