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

@Entity
@Table(name = "provider")
public class Provider implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7246472106057374482L;
	@Id
	@GeneratedValue(generator = "provider")
	private Integer id;
	private String name;
	private String address;
	private Integer telephone;
	private String email;
	
	@OneToMany(mappedBy = "provider", fetch = FetchType.LAZY)
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
		return "Provider [providerId=" + id + ", name=" + name + ", adress=" + address + ", telephone="
				+ telephone + ", email=" + email + ", arrivals=" + arrivals + "]";
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

	public String getAdress() {
		return address;
	}

	public void setAdress(String adress) {
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
