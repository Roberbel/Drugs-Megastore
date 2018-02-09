package pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3781798634843187795L;
	private Integer id;
	private Integer pc;
	private String country;
	private String adress;
	private Integer phone;
	private String city;
	private List<Employee> employees;
	private List<Corridors> corridor;

	public Warehouse() {
		super();
		this.employees = new ArrayList<Employee>();
	}
<<<<<<< HEAD
	public Warehouse(Integer id, Integer pc, String country, String adress, String city,Integer phone, List<Employee> employees, List<Corridors>corridor) {
		
		super();
		this.id=id;
		this.pc=pc;
		this.country=country;
		this.adress=adress;
		this.phone=phone;
		this.employees=employees;
        this.corridor=corridor;	
        this.city=city;
	}
	
public Warehouse(Integer id, Integer pc, String country, String adress,Integer phone, String city) {
		
		super();
		this.id=id;
		this.pc=pc;
		this.country=country;
		this.adress=adress;
		this.phone=phone;
		this.city=city;
		this.employees=new ArrayList<Employee>();
        this.corridor=new ArrayList<Corridors>();	
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
	Warehouse other = (Warehouse) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
}
@Override
public String toString() {
	return "Warehouse [id=" + id + ", pc=" + pc + ", country=" + country + ", adress=" + adress + ", phone=" + phone +", city="
			
		+ city	+ "]";
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getPc() {
	return pc;
}
public void setPc(Integer pc) {
	this.pc = pc;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getAdress() {
	return adress;
}
public void setAdress(String adress) {
	this.adress = adress;
}
public Integer getPhone() {
	return phone;
}
public void setPhone(Integer phone) {
	this.phone = phone;
}
public List<Employee> getEmployees() {
	return employees;
}
public void setEmployees(List<Employee> employees) {
	this.employees = employees;
}
public List<Corridors> getCorridor() {
	return corridor;
}
public void setCorridor(List<Corridors> corridor) {
	this.corridor = corridor;
}
	
=======
	
	

	public Warehouse(Integer pc, String country, String adress, Integer phone, List<Employee> employees,
			List<Corridors> corridor) {
		super();
		this.pc = pc;
		this.country = country;
		this.adress = adress;
		this.phone = phone;
		this.employees = employees;
		this.corridor = corridor;
	}



	public Warehouse(Integer id, Integer pc, String country, String adress, Integer phone, List<Employee> employees,
			List<Corridors> corridor) {

		super();
		this.id = id;
		this.pc = pc;
		this.country = country;
		this.adress = adress;
		this.phone = phone;
		this.employees = employees;
		this.corridor = corridor;
	}

	public Warehouse(Integer id, Integer pc, String country, String adress, Integer phone) {

		super();
		this.id = id;
		this.pc = pc;
		this.country = country;
		this.adress = adress;
		this.phone = phone;
		this.employees = new ArrayList<Employee>();
		this.corridor = new ArrayList<Corridors>();
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
		Warehouse other = (Warehouse) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
>>>>>>> fa3521681d05b9a6a576c7189a21fcb54f3d2a50

	@Override
	public String toString() {
		return "Warehouse [id=" + id + ", pc=" + pc + ", country=" + country + ", adress=" + adress + ", phone=" + phone
				+ "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPc() {
		return pc;
	}

	public void setPc(Integer pc) {
		this.pc = pc;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<Corridors> getCorridor() {
		return corridor;
	}

	public void setCorridor(List<Corridors> corridor) {
		this.corridor = corridor;
	}

	public void addEmployee(Employee employee) {
		if (!employees.contains(employee)) {
			this.employees.add(employee);
		}
	}

	public void removeEmployee(Employee employee) {
		if (employees.contains(employee)) {
			this.employees.remove(employee);
		}
	}

	public void addCorridor(Corridors corridors) {
		if (!corridor.contains(corridors)) {
			this.corridor.add(corridors);
		}
	}

	public void removeCorridor(Corridors corridors) {
		if (corridor.contains(corridors)) {
			this.corridor.remove(corridors);
		}
	}

}
