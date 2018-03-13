package pojos;


import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;





public class Corridor implements Serializable {

	
	private static final long serialVersionUID = 4227526298151577502L;
	
	private Integer id;
	private float temperature;
	private Warehouse warehouse;
	private List<Drug>drugs;
	
	
	public Corridor() {
		super();
		this.drugs=new ArrayList<Drug>();
	
	}
	
		
	public Corridor(float temperature, Warehouse warehouse, List<Drug> drugs) {
		super();
		this.temperature = temperature;
		this.warehouse = warehouse;
		this.drugs = drugs;
	}

	public Corridor(Integer id, float temperature, Warehouse warehouse) {
		super();
		this.id =id;
		this.temperature = temperature;
		this.warehouse = warehouse;
		this.drugs = new ArrayList<Drug>();
	}
	public Corridor(Integer id, float temperature, Warehouse warehouse,
			List<Drug> drugs) {
		super();
		this.id =id;
		this.temperature = temperature;
		this.warehouse = warehouse;
		this.drugs = drugs;
	}
	public Corridor(Integer id, float temperature) {
		super();
		this.id =id;
		this.temperature = temperature;
		this.drugs = new ArrayList<Drug>();
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
		Corridor other = (Corridor) obj;
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

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public List<Drug> getDrugs() {
		return drugs;
	}

	public void setDrugs(List<Drug> drugs) {
		this.drugs = drugs;
	}

	public void addDrug(Drug drug) {
		if (!drugs.contains(drug)) {
			this.drugs.add(drug);
		}
	}


	public void removeDrug(Drug drug) {
		if (drugs.contains(drug)) {
			this.drugs.remove(drug);
		}
	}
	
}
