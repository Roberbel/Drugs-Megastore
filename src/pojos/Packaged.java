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
@Table(name = "packaged")
@IdClass(DeliveryAssociationId.class)
public class Packaged implements Serializable{

	private static final long serialVersionUID = 3515016001673617510L;

	@Id
	@Column(name="transaction_id")
	private Integer deliveryId;
	
	@Id
	@Column(name="drug_id")
	private Integer drugId;
	
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "drug_id", referencedColumnName = "id")
	private Drug drug;
	
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "transaction_id", referencedColumnName = "transaction_id")
	private Delivery delivery;
	
	private Integer amount;


	public Packaged() {
		super();
	}
	
	public Packaged(Integer drugId, Integer deliveryId, Integer amount) {
		
		super();
		this.drugId = drugId;
		this.deliveryId = deliveryId;
		this.amount = amount;
		
	}
	
	public Packaged(Drug drug, Delivery delivery, Integer amount) {
		
		super();
		this.deliveryId = delivery.getTransactionId();
		this.drugId = drug.getId();
		drug.addPackaged(this);
		this.drug = drug;
		this.delivery = delivery;
		this.amount = amount;
	
	}

	public Integer getDeliveryId() {
	
		return deliveryId;
	}

	public void setDeliveryId(Integer deliveryId) {

		this.deliveryId = deliveryId;
	
	}

	public Integer getDrugId() {
	
		return drugId;
	
	}

	public void setDrugId(Integer drugId) {
	
		this.drugId = drugId;
	
	}

	public Drug getDrug() {
	
		return drug;
	
	}

	public void setDrug(Drug drug) {
	
		this.drug = drug;
	
	}

	public Delivery getDelivery() {
	
		return delivery;
	
	}

	public void setDelivery(Delivery delivery) {
	
		this.delivery = delivery;
	
	}

	public Integer getAmount() {
		
		return amount;
	
	}

	public void setAmount(Integer ammount) {
	
		this.amount = ammount;
	
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((deliveryId == null) ? 0 : deliveryId.hashCode());
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
		Packaged other = (Packaged) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (deliveryId == null) {
			if (other.deliveryId != null)
				return false;
		} else if (!deliveryId.equals(other.deliveryId))
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
		return "Packaged [drug=" + drug + ", delivery=" + delivery + ", ammount=" + amount + "]";
	}
			
}
