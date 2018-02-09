package pojo;

import java.io.Serializable;
import java.util.*;


public class Client implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 2116743967910216027L;

	private Integer id;
	private String name;
	private String adress;
	private Integer telephone;
	private String email;
	private String paymentMethod;
	private List<Deliveries> deliveries;

	
	
	public Client() {
		super();
		setDeliveries(new ArrayList<Deliveries>());
	}
	
	public Client(Integer id, String name, String adress, String paymentMethod, ArrayList<Deliveries> deliveries) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.paymentMethod = paymentMethod;
		this.deliveries = deliveries;
	}

	public Client(Integer id, String name, String adress, Integer telephone, String email, String paymentMethod, ArrayList<Deliveries> deliveries) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.telephone = telephone;
		this.email = email;
		this.paymentMethod = paymentMethod;
		this.deliveries = deliveries;

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
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", adress=" + adress + ", telephone=" + telephone + ", email="
				+ email + ", paymentMethod=" + paymentMethod + ", deliveries=" + deliveries + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public List<Deliveries> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(List<Deliveries> deliveries) {
		this.deliveries = deliveries;
	}
	
	public void addDelivery(Deliveries delivery) {
		if (!deliveries.contains(delivery)) {
			this.deliveries.add(delivery);
		}
	}

	public void removeDelivery(Deliveries delivery) {
		if (!deliveries.contains(delivery)) {
			this.deliveries.remove(delivery);
		}
	}
	
}
