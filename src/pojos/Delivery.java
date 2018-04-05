package pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.*;

import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "deliveries")
public class Delivery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2324788895880344002L;

	private Integer id;
	private Integer sellingPrice;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	private Date transactionDate;

	@OneToMany(mappedBy = "drugId")
	private List<Drug> drugs;
	

	public Delivery() {
		super();
		drugs = new ArrayList<Drug>();
	}

	
	
	public Delivery(Integer sellingPrice, List<Integer> ammount, Date transactionDate, List<Drug> drugId, Client client) {
		super();
		this.sellingPrice = sellingPrice;
		this.ammount = ammount;
		this.transactionDate = transactionDate;
		this.drugs = drugId;
		this.client = client;
	}



	public Delivery(Integer transactionId, Integer sellingPrice, List<Integer> ammount, Date transactionDate,
			List<Drug> drugId, Client client) {
		super();
		this.id = transactionId;
		this.sellingPrice = sellingPrice;
		this.ammount = ammount;
		this.transactionDate = transactionDate;
		this.drugs = drugId;
		this.client = client;
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
		Delivery other = (Delivery) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Deliveries [transactionId=" + id + ", sellingPrice=" + sellingPrice + ", ammount=" + ammount
				+ ", transactionDate=" + transactionDate + ", drugId=" + drugs + ", client=" + client + "]";
	}

	public Integer getTransactionId() {
		return id;
	}

	public void setTransactionId(Integer transactionId) {
		this.id = transactionId;
	}

	public Integer getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Integer sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public List<Integer> getAmmount() {
		return ammount;
	}

	public void setAmmount(List<Integer> ammount) {
		this.ammount = ammount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public List<Drug> getDrugId() {
		return drugs;
	}

	public void setDrugId(List<Drug> clientId) {
		this.drugs = clientId;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
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
