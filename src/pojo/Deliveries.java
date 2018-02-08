package pojo;

import java.io.Serializable;
import java.sql.Date;
import java.util.*;

public class Deliveries implements Serializable {

	private Integer transactionId;
	private Integer sellingPrice;
	private Integer ammount;
	private Date transactionDate;
	private List<Client> clientId;

	public Deliveries() {
		super();
		clientId = new ArrayList<Client>();
	}

	public Deliveries(Integer transactionId, Integer sellingPrice, Integer ammount, Date transactionDate,List<Client> clientId) {
		super();
		this.transactionId = transactionId;
		this.sellingPrice = sellingPrice;
		this.ammount = ammount;
		this.transactionDate = transactionDate;
		this.clientId = clientId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((transactionId == null) ? 0 : transactionId.hashCode());
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
		Deliveries other = (Deliveries) obj;
		if (transactionId == null) {
			if (other.transactionId != null)
				return false;
		} else if (!transactionId.equals(other.transactionId))
			return false;
		return true;
	}
	
	

	@Override
	public String toString() {
		return "Deliveries [transactionId=" + transactionId + ", sellingPrice=" + sellingPrice + ", ammount=" + ammount
				+ ", transactionDate=" + transactionDate + ", clientId=" + clientId + "]";
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Integer sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Integer getAmmount() {
		return ammount;
	}

	public void setAmmount(Integer ammount) {
		this.ammount = ammount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public List<Client> getClientId() {
		return clientId;
	}

	public void setClientId(List<Client> clientId) {
		this.clientId = clientId;
	}
	
	

}
