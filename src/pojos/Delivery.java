package pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.*;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "deliveries")
public class Delivery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2324788895880344002L;
	
	@Id
	@Column (name = "transaction_id")
	private Integer id;
	@Column (name = "selling_price")
	private Integer sellingPrice;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	@Column (name = "transaction_date")
	private Date transactionDate;

	@OneToMany(mappedBy = "delivery", fetch = FetchType.LAZY)
	private List<Packaged> packages;
	

	public Delivery() {
		super();
		packages = new ArrayList<Packaged>();
		sellingPrice = 1000000;
		transactionDate = new Date(System.currentTimeMillis());
	}

	
	
	public Delivery(Integer sellingPrice, Date transactionDate, List<Packaged> packages, Client client) {
		super();
		this.sellingPrice = sellingPrice;
		this.transactionDate = transactionDate;
		this.packages = packages;
		this.client = client;
	}



	public Delivery(Integer transactionId, Integer sellingPrice, Date transactionDate,
			List<Packaged> packages, Client client) {
		super();
		this.id = transactionId;
		this.sellingPrice = sellingPrice;
		this.transactionDate = transactionDate;
		this.packages = packages;
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
		return "Deliveries [transactionId=" + id + ", sellingPrice=" + sellingPrice + ", ammount="
				+ ", transactionDate=" + transactionDate + ", drugId=" + packages + ", client=" + client + "]";
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

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public List<Packaged> getPackages() {
		return packages;
	}

	public void setPackages(List<Packaged> packages) {
		this.packages = packages;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	public void addPackaged(Drug drug, Integer amount) {
		Packaged packaged = new Packaged(drug, this, amount);
		if (!packages.contains(packaged)) {
			this.packages.add(packaged);
		}
	}


	public void removePackaged(Packaged packaged) {
		if (packages.contains(packaged)) {
			this.packages.remove(packaged);
		}
	}

}
