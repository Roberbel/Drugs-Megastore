package pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.*;

public class Delivery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2324788895880344002L;

	private Integer transactionId;
	private Integer sellingPrice;
	private List<Integer> amount;
	private Date transactionDate;
	private boolean deliveried;
	private List<Drug> drugId;
	private Client client;

	public Delivery() {
		super();
		drugId = new ArrayList<Drug>();
	}

	
	
	public Delivery(Integer sellingPrice, List<Integer> amount, Date transactionDate, List<Drug> drugId, Client client) {
		super();
		this.sellingPrice = sellingPrice;
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.deliveried=false;
		this.drugId = drugId;
		this.client = client;
	}



	public Delivery(Integer transactionId, Integer sellingPrice, List<Integer> amount, Date transactionDate,
			List<Drug> drugId, Client client) {
		super();
		this.transactionId = transactionId;
		this.sellingPrice = sellingPrice;
		this.amount = amount;
		this.deliveried=false;
		this.transactionDate = transactionDate;
		this.drugId = drugId;
		this.client = client;
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
		Delivery other = (Delivery) obj;
		if (transactionId == null) {
			if (other.transactionId != null)
				return false;
		} else if (!transactionId.equals(other.transactionId))
			return false;
		return true;
	}

	

	@Override
	public String toString() {
		return "Delivery [transactionId=" + transactionId + ", sellingPrice=" + sellingPrice + ", amount=" + amount
				+ ", transactionDate=" + transactionDate + ", deliveried=" + deliveried + ", drugId=" + drugId
				+ ", client=" + client + "]";
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

	public List<Integer> getAmount() {
		return amount;
	}

	public void setAmount(List<Integer> amount) {
		this.amount = amount;
	}
	
	public void addAmount(Integer amounts) {
		if(!amount.contains(amounts)) {
			amount.add(amounts);
		}
	}
	
	public void removeAmount(Integer amounts) {
		if(amount.contains(amounts)) {
			amount.remove(amounts);
		}
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public boolean isDeliveried() {
		return deliveried;
	}



	public void setDeliveried(boolean deliveried) {
		this.deliveried = deliveried;
	}



	public List<Drug> getDrugId() {
		return drugId;
	}

	public void setDrugId(List<Drug> clientId) {
		this.drugId = clientId;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	public void addDrug(Drug drug) {
		if (!drugId.contains(drug)) {
			this.drugId.add(drug);
		}
	}


	public void removeDrug(Drug drug) {
		if (drugId.contains(drug)) {
			this.drugId.remove(drug);
		}
	}

}
