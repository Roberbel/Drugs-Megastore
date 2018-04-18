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
	
	
	/*
	 * TODO:
	 * constructors
	 * getters and setters
	 */
	
	
}
