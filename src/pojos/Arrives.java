package pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "arrives")
@IdClass(ArrivalAssociationId.class)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Arrives")
@XmlType
public class Arrives implements Serializable{
	
	private static final long serialVersionUID = -7414814044025259619L;
	@Id
	@Column(name = "drug_id")
	@XmlAttribute
	private Integer drugId;
	@Id
	@Column(name = "transaction_id")
	@XmlAttribute
	private Integer arrivalId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn(name = "drug_id", referencedColumnName = "id")
	@XmlTransient
	private Drug drug;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn(name = "transaction_id", referencedColumnName = "transaction_id")
	@XmlTransient
	private Arrival arrival;
	
	@XmlAttribute
	private Integer amount;

	public Arrives(){
	
		super();
		amount = 90;
	
	}
	
	public Arrives(Integer drugId, Integer arrivalId, Integer amount) {
		this.drugId = drugId;
		this.arrivalId = arrivalId;
		this.amount = amount;
		drug = null;
		arrival = null;
	}
	
	
	
	public Arrives(Integer drugId, Integer arrivalId, Integer amount, Drug drug) {
		super();
		this.drugId = drugId;
		this.arrivalId = arrivalId;
		this.drug = drug;
		this.amount = amount;
	}

	public Arrives(Drug drug, Arrival arrival, Integer amount){
		
		super();
		drug.addArrive(this);
		this.drug = drug;
		this.arrival = arrival;
		this.drugId = drug.getId();
		this.arrivalId = arrival.getArrivalId();
		this.amount = amount;
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((arrivalId == null) ? 0 : arrivalId.hashCode());
		result = prime * result + ((drugId == null) ? 0 : drugId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arrives other = (Arrives) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (arrivalId == null) {
			if (other.arrivalId != null)
				return false;
		} else if (!arrivalId.equals(other.arrivalId))
			return false;
		if (drugId == null) {
			if (other.drugId != null)
				return false;
		} else if (!drugId.equals(other.drugId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Arrives [drug=" + drug + ", arrival=" + arrival + ", amount=" + amount + "]";
	}
	
	
}
