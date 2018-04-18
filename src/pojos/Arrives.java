package pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "arrives")
@IdClass(ArrivalAssociationId.class)
public class Arrives implements Serializable{
	
	private static final long serialVersionUID = -7414814044025259619L;
	@Id
	@Column(name = "drug_id")
	private Integer drugId;
	@Id
	@Column(name = "transaction_id")
	private Integer arrivalId;
	
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "drug_id", referencedColumnName = "id")	
	private Drug drug;
	
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "transaction_id", referencedColumnName = "transaction_id")
	private Arrival arrival;
	
	private Integer amount;

	Arrives(){
	
		super();
	
	}
	
	Arrives(Drug drug, Arrival arrival, Integer ammount){
		
		super();
		this.drug = drug;
		this.arrival = arrival;
		this.drugId = drug.getId();
		this.arrivalId = arrival.getArrivalId();
		this.amount = ammount;
	
	}

	public Integer getDrugId() {
	
		return drugId;
	
	}


	public void setDrugId(Integer drugId) {
	
		this.drugId = drugId;
	
	}


	public Integer getArrivalId() {
	
		return arrivalId;
	
	}


	public void setArrivalId(Integer arrivalId) {
	
		this.arrivalId = arrivalId;
	
	}


	public Drug getDrug() {
	
		return drug;
	
	}


	public void setDrug(Drug drug) {
	
		this.drug = drug;
	
	}


	public Arrival getArrival() {
	
		return arrival;
	
	}


	public void setArrival(Arrival arrival) {
	
		this.arrival = arrival;
	
	}


	public Integer getAmount() {
	
		return amount;
	
	}


	public void setAmount(Integer amount) {
	
		this.amount = amount;
	
	}
	
}
