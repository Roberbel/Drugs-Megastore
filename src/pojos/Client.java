package pojos;

import java.io.Serializable;
import java.util.*;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

@Entity
@Table(name = "client")
public class Client extends User implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 2116743967910216027L;

	@Id
	@GeneratedValue(generator = "client")
	@TableGenerator(name="client", table="sqlite_sequence",
    pkColumnName="name", valueColumnName="seq", pkColumnValue="client")
	private Integer id;
	private String name;
	private String address;
	private Integer telephone;
	private String email;
	@Column(name = "payment_method")
	private PaymentMethod paymentMethod;
	@OneToMany(fetch = FetchType.LAZY)
	private List<Delivery> deliveries;
	
	public enum PaymentMethod {PAYPAL, VISA, MASTERCARD, AMERICAN_EXPRESS, ORGANS};
	
	
	public Client() {
		
		super();
		setDeliveries(new ArrayList<Delivery>());
		name = "Khorne";
		address = "c/Viena 17 cabanillas del campo";
		paymentMethod = PaymentMethod.ORGANS;
		username = (name+(int)(Math.random()*10));
		password = ("Cualquier_cosa");
	}
	
	
	
	public Client(String name, String adress, Integer telephone, String email, String paymentMethod,
			List<Delivery> deliveries, String userName, String password) {
		super(userName, password);
		this.name = name;
		this.address = adress;
		this.telephone = telephone;
		this.email = email;
		if (paymentMethod.equals("PAYPAL")) {
			this.paymentMethod = PaymentMethod.PAYPAL;
		}else if(paymentMethod.equals("VISA")) {
			this.paymentMethod = PaymentMethod.VISA;
		}else if(paymentMethod.equals("MASTERCARD")) {
			this.paymentMethod = PaymentMethod.MASTERCARD;
		}else if(paymentMethod.equals("AMERICAN_EXPRESS")) {
			this.paymentMethod = PaymentMethod.AMERICAN_EXPRESS;
		}else if(paymentMethod.equals("ORGANS")) {
			this.paymentMethod = PaymentMethod.ORGANS;
		}
		this.deliveries = deliveries;
	}



	public Client(Integer id, String name, String adress,Date date, String paymentMethod, ArrayList<Delivery> deliveries, String username, String password) {
		super(username, password, id);
		this.name = name;
		this.address = adress;
		if (paymentMethod.equals("PAYPAL")) {
			this.paymentMethod = PaymentMethod.PAYPAL;
		}else if(paymentMethod.equals("VISA")) {
			this.paymentMethod = PaymentMethod.VISA;
		}else if(paymentMethod.equals("MASTERCARD")) {
			this.paymentMethod = PaymentMethod.MASTERCARD;
		}else if(paymentMethod.equals("AMERICAN_EXPRESS")) {
			this.paymentMethod = PaymentMethod.AMERICAN_EXPRESS;
		}else if(paymentMethod.equals("ORGANS")) {
			this.paymentMethod = PaymentMethod.ORGANS;
		}
		this.deliveries = deliveries;
	}

	public Client(Integer id, String name, String adress, Integer telephone, String email, String paymentMethod,
			ArrayList<Delivery> deliveries, String username, String password) {
		super(username, password, id);
		this.name = name;
		this.address = adress;
		this.telephone = telephone;
		this.email = email;
		if (paymentMethod.equals("PAYPAL")) {
			this.paymentMethod = PaymentMethod.PAYPAL;
		}else if(paymentMethod.equals("VISA")) {
			this.paymentMethod = PaymentMethod.VISA;
		}else if(paymentMethod.equals("MASTERCARD")) {
			this.paymentMethod = PaymentMethod.MASTERCARD;
		}else if(paymentMethod.equals("AMERICAN_EXPRESS")) {
			this.paymentMethod = PaymentMethod.AMERICAN_EXPRESS;
		}else if(paymentMethod.equals("ORGANS")) {
			this.paymentMethod = PaymentMethod.ORGANS;
		}
		this.deliveries = deliveries;

	}
	public Client(Integer id, String name, String adress, Integer telephone, String email, String paymentMethod, String username, String password) {
		super( username, password, id);
		this.name = name;
		this.address = adress;
		this.telephone = telephone;
		this.email = email;
		if (paymentMethod.equals("PAYPAL")) {
			this.paymentMethod = PaymentMethod.PAYPAL;
		}else if(paymentMethod.equals("VISA")) {
			this.paymentMethod = PaymentMethod.VISA;
		}else if(paymentMethod.equals("MASTERCARD")) {
			this.paymentMethod = PaymentMethod.MASTERCARD;
		}else if(paymentMethod.equals("AMERICAN_EXPRESS")) {
			this.paymentMethod = PaymentMethod.AMERICAN_EXPRESS;
		}else if(paymentMethod.equals("ORGANS")) {
			this.paymentMethod = PaymentMethod.ORGANS;
		}
		
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
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
		if (getId() == null) {
			if (other.getId()!= null)
				return false;
		} else if (!super.getId().equals(other.getId()))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "Client [id=" + super.getId() + ", name=" + name + ", adress=" + address + ", telephone=" + telephone + ", email="
				+ email + ", paymentMethod=" + paymentMethod + ", deliveries=" + deliveries +", user=" + username +", password=" +password + "]";
	}

	public Integer getId() {
		return super.getId();
	}

	public void setId(Integer id) {
		super.setId(id);
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
	public String getUser() {
		return username;
	}
	
	 
	public void setUser(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	
	 
	public void setPassword(String password) {
		this.password = password;
	}
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
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
