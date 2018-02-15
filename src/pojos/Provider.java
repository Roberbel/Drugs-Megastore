package pojo;

import java.io.Serializable;
import java.util.*;

public class Provider implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7246472106057374482L;
	private Integer providerId;
	private String name;
	private String adress;
	private Integer telephone;
	private String email;
	private List<Arrivals> arrivals;

	public Provider() {
		super();
		arrivals = new ArrayList<Arrivals>();
	}

	
	
	public Provider(String name, String adress, Integer telephone, String email, List<Arrivals> arrivals) {
		super();
		this.name = name;
		this.adress = adress;
		this.telephone = telephone;
		this.email = email;
		this.arrivals = arrivals;
	}



	public Provider(Integer providerId, String name, String adress, List<Arrivals> arrivals) {
		super();
		this.providerId = providerId;
		this.name = name;
		this.adress = adress;
		this.arrivals = arrivals;
	}

	public Provider(Integer providerId, String name, String adress, Integer telephone, String email,
			List<Arrivals> arrivals) {
		super();
		this.providerId = providerId;
		this.name = name;
		this.adress = adress;
		this.telephone = telephone;
		this.email = email;
		this.arrivals = arrivals;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((providerId == null) ? 0 : providerId.hashCode());
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
		if (providerId == null) {
			if (other.providerId != null)
				return false;
		} else if (!providerId.equals(other.providerId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Provider [providerId=" + providerId + ", name=" + name + ", adress=" + adress + ", telephone="
				+ telephone + ", email=" + email + ", arrivals=" + arrivals + "]";
	}

	public Integer getProviderId() {
		return providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
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

	public List<Arrivals> getArrivals() {
		return arrivals;
	}

	public void setArrivals(List<Arrivals> arrivals) {
		this.arrivals = arrivals;
	}
	
	public void addArrival(Arrivals arrival) {
		if(!arrivals.contains(arrival)) {
			arrivals.add(arrival);
		}
	}
	
	public void removeArrival(Arrivals arrival) {
		if(arrivals.contains(arrival)) {
			arrivals.remove(arrival);
		}
	}

}
