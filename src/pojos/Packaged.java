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
	
	private Integer ammount;
	
	/*
	 * TODO:
	 * constructors
	 * getters and setters
	 */
	
	
}
