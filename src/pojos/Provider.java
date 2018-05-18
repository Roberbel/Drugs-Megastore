package pojos;

import java.io.Serializable;
import java.util.*;

import javax.persistence.Basic;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "provider")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Provider")
@XmlType
public class Provider implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7246472106057374482L;
	@Id
	@GeneratedValue(generator = "provider")
	@TableGenerator(name="provider", table="sqlite_sequence",
    pkColumnName="name", valueColumnName="seq", pkColumnValue="provider")
	@XmlAttribute
	private Integer id;
	
	@XmlAttribute
	private String name;
	
	@XmlAttribute
	private String address;
	
	@XmlAttribute
	private Integer telephone;
	
	@XmlAttribute
	private String email;
	
	@OneToMany(mappedBy = "provider", fetch = FetchType.LAZY)
	@XmlTransient
	private List<Arrival> arrivals;

	public Provider() {
		super();
		arrivals = new ArrayList<Arrival>();
		name = "Provider";
		address = "C/Del recuerdo 45";
	}

	
	
	public Provider(String name, String adress, Integer telephone, String email, List<Arrival> arrivals) {
		super();
		this.name = name;
		this.address = adress;
		this.telephone = telephone;
		this.email = email;
		this.arrivals = arrivals;
	}



	public Provider(Integer providerId, String name, String adress, List<Arrival> arrivals) {
		super();
		this.id = providerId;
		this.name = name;
		this.address = adress;
		this.arrivals = arrivals;
	}

	public Provider(Integer providerId, String name, String adress, Integer telephone, String email,
			List<Arrival> arrivals) {
		super();
		this.id = providerId;
		this.name = name;
		this.address = adress;
		this.telephone = telephone;
		this.email = email;
		this.arrivals = arrivals;
	}

	public Provider(Integer providerId, String name, String adress, Integer telephone, String email) {
		super();
		this.id = providerId;
		this.name = name;
		this.address = adress;
		this.telephone = telephone;
		this.email = email;
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
		Provider other = (Provider) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name ;
	}

	public Integer getProviderId() {
		return id;
	}

	public void setProviderId(Integer providerId) {
		this.id = providerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String adress) {
		this.address = adress;
	}

	public Integer getTelephone() {
		return telephone;
	}

	public void setTelephone(Integer telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Arrival> getArrivals() {
		return arrivals;
	}

	public void setArrivals(List<Arrival> arrivals) {
		this.arrivals = arrivals;
	}
	
	public void addArrival(Arrival arrival) {
		if(!arrivals.contains(arrival)) {
			arrivals.add(arrival);
		}
	}
	
	public void removeArrival(Arrival arrival) {
		if(arrivals.contains(arrival)) {
			arrivals.remove(arrival);
		}
	}

}
