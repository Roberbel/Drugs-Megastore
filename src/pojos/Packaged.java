package pojos;

import java.io.Serializable;

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
	private Integer deliveryId;
	@Id
	private Integer drugId;
	
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "drugId", referencedColumnName = "id")
	private Drug drug;
	
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "deliveryId", referencedColumnName = "id")
	private Delivery delivery;
	
	private Integer ammount;
	
	/*
	 * TODO:
	 * constructors
	 * getters and setters
	 */
	
	
}
