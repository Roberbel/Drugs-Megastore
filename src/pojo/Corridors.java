package pojo;


import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;





public class Corridors implements Serializable {

	
	private static final long serialVersionUID = 4227526298151577502L;
	
	private Integer id;
	private Integer temperature;
	private Warehouse warehouse;
	private List<Drugs>drugs;
	
	
	public Corridors() {
		super();
		this.drugs=new ArrayList<Drugs>();
	
	}
	
		
	public Corridors(Integer temperature, Warehouse warehouse, List<Drugs> drugs) {
		super();
		this.temperature = temperature;
		this.warehouse = warehouse;
		this.drugs = drugs;
	}

	public Corridors(Integer id, Integer temperature, Warehouse warehouse) {
		super();
		this.id =id;
		this.temperature = temperature;
		this.warehouse = warehouse;
		this.drugs = new ArrayList<Drugs>();
	}
	public Corridors(Integer id, Integer temperature, Warehouse warehouse,
			List<Drugs> drugs) {
		super();
		this.id =id;
		this.temperature = temperature;
		this.warehouse = warehouse;
		this.drugs = drugs;
	}
	public Corridors(Integer id, Integer temperature) {
		super();
		this.id =id;
		this.temperature = temperature;
		this.drugs = new ArrayList<Drugs>();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Corridors other = (Corridors) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Corridors [id=" + id + ", temperature=" + temperature + ", warehouse=" + warehouse + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTemperature() {
		return temperature;
	}

	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public List<Drugs> getDrugs() {
		return drugs;
	}

	public void setDrugs(List<Drugs> drugs) {
		this.drugs = drugs;
	}

	public void addDrug(Drugs drug) {
		if (!drugs.contains(drug)) {
			this.drugs.add(drug);
		}
	}


	public void removeDrug(Drugs drug) {
		if (drugs.contains(drug)) {
			this.drugs.remove(drug);
		}
	}
	
}
