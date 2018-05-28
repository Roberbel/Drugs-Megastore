package pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "warehouse")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Warehouse")
@XmlType(propOrder = { "employees", "corridors" })
public class Warehouse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3781798634843187795L;
	@Id
	@GeneratedValue(generator = "warehouse")
	@TableGenerator(name="warehouse", table="sqlite_sequence",
    pkColumnName="name", valueColumnName="seq", pkColumnValue="warehouse")
	@XmlAttribute
	private Integer id;
	
	@XmlAttribute
	private Integer pc;
	
	@XmlAttribute
	private String country;
	
	@XmlAttribute
	private String city;
	
	@XmlAttribute
	private String address;
	
	@XmlAttribute
	private Integer phone;
	
	@XmlElement(name = "Employee")
	@XmlElementWrapper(name = "Employees")
	@OneToMany(mappedBy="warehouse", fetch = FetchType.LAZY)
	private List<Employee> employees;
	
	@XmlElement(name = "Corridor")
	@XmlElementWrapper(name = "Corridors")
	@OneToMany(mappedBy="warehouse", fetch = FetchType.LAZY)
	private List<Corridor> corridors;

	public Warehouse() {
		super();
		this.employees = new ArrayList<Employee>();
		pc = 28045;
		country = "Spain";
		city = "Madrid";
		address = "c/San Juan de los reyes";
		phone = 908874632;
	}
	
	

	public Warehouse(Integer pc, String country, String city, String adress, Integer phone, List<Employee> employees,
			List<Corridor> corridors) {
		super();
		this.pc = pc;
		this.country = country;
		this.address = adress;
		this.city = city;
		this.phone = phone;
		this.employees = employees;
		this.corridors = corridors;
	}



	public Warehouse(Integer id, Integer pc, String country,String city, String adress, Integer phone, List<Employee> employees,
			List<Corridor> corridors) {

		super();
		this.id = id;
		this.pc = pc;
		this.country = country;
		this.city = city;
		this.address = adress;
		this.phone = phone;
		this.employees = employees;
		this.corridors = corridors;
	}

	public Warehouse(Integer id, Integer pc, String country,String city, String adress, Integer phone) {

		super();
		this.id = id;
		this.pc = pc;
		this.country = country;
		this.city = city;
		this.address = adress;
		this.phone = phone;
		this.employees = new ArrayList<Employee>();
		this.corridors = new ArrayList<Corridor>();
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

//	@Override
//	public String toString() {
//		return "Warehouse [id=" + id + ", pc=" + pc + ", country=" + country + ", adress=" + address + ", phone=" + phone
//				+ "]";
//	}
	
	public String toString() {
		return "Warehouse " + this.id;
	}

	public Integer getId() {
		return id;
	}

	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String adress) {
		this.address = adress;
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

	public List<Corridor> getCorridor() {
		return corridors;
	}

	public void setCorridors(List<Corridor> corridor) {
		this.corridors = corridor;
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

	public void addCorridor(Corridor corridor) {
		if (!corridors.contains(corridor)) {
			this.corridors.add(corridor);
		}
	}

	public void removeCorridor(Corridor corridor) {
		if (corridors.contains(corridor)) {
			this.corridors.remove(corridor);
		}
	}

}
