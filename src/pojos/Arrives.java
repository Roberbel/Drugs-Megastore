package pojos;

import java.io.Serializable;

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
	private Integer drugId;
	@Id
	private Integer arrivalId;
	
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "drugId", referencedColumnName = "id")	
	private Drug drug;
	
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "arrivalId", referencedColumnName = "id")
	private Arrival arrival;
	
	
	private Integer ammount;
	
	
	/*
	 * TODO:
	 * constructors
	 * getters and setters
	 */
	
	
}
