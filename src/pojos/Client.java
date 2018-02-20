package pojos;

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
	private List<Delivery> deliveries;

	
	
	public Client() {
		super();
		setDeliveries(new ArrayList<Delivery>());
	}
	
	
	
	public Client(String name, String adress, Integer telephone, String email, String paymentMethod,
			List<Delivery> deliveries) {
		super();
		this.name = name;
		this.adress = adress;
		this.telephone = telephone;
		this.email = email;
		this.paymentMethod = paymentMethod;
		this.deliveries = deliveries;
	}



	public Client(Integer id, String name, String adress, String paymentMethod, ArrayList<Delivery> deliveries) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.paymentMethod = paymentMethod;
		this.deliveries = deliveries;
	}

	public Client(Integer id, String name, String adress, Integer telephone, String email, String paymentMethod,
			ArrayList<Delivery> deliveries) {
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

	public List<Delivery> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(List<Delivery> deliveries) {
		this.deliveries = deliveries;
	}
	
	public void addDelivery(Delivery delivery) {
		if (!deliveries.contains(delivery)) {
			this.deliveries.add(delivery);
		}
	}

	public void removeDelivery(Delivery delivery) {
		if (deliveries.contains(delivery)) {
			this.deliveries.remove(delivery);
		}
	}
	
}
