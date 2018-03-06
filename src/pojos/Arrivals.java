package pojos;

import java.io.Serializable;
import java.util.*;

public class Arrivals implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2841430439397985029L;
	private Integer arrivalId;
	private Integer buyingPrice;
	private Date date;
	private Integer ammount;
	private Provider provider;
	private List<Drugs> drugs;

	public Arrivals() {
		super();
		drugs = new ArrayList<Drugs>();
	}

	
	
	public Arrivals(Integer buyingPrice, Date date, Integer ammount, Provider provider, List<Drugs> drugs) {
		super();
		this.buyingPrice = buyingPrice;
		this.date = date;
		this.ammount = ammount;
		this.provider = provider;
		this.drugs = drugs;
	}



	public Arrivals(Integer arrivalId, Integer buyingPrice, Date date, Integer ammount, Provider provider,
			List<Drugs> drugs) {
		super();
		this.arrivalId = arrivalId;
		this.buyingPrice = buyingPrice;
		this.date = date;
		this.ammount = ammount;
		this.provider = provider;
		this.drugs = drugs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrivalId == null) ? 0 : arrivalId.hashCode());
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
		Arrivals other = (Arrivals) obj;
		if (arrivalId == null) {
			if (other.arrivalId != null)
				return false;
		} else if (!arrivalId.equals(other.arrivalId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Arrivals [arrivalId=" + arrivalId + ", buyingPrice=" + buyingPrice + ", date=" + date + ", ammount="
				+ ammount + ", provider=" + provider + ", drugs=" + drugs + "]";
	}

	public Integer getArrivalId() {
		return arrivalId;
	}

	public void setArrivalId(Integer arrivalId) {
		this.arrivalId = arrivalId;
	}

	public Integer getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(Integer buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getAmmount() {
		return ammount;
	}

	public void setAmmount(Integer ammount) {
		this.ammount = ammount;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public List<Drugs> getDrugs() {
		return drugs;
	}

	public void setDrugs(List<Drugs> drugs) {
		this.drugs = drugs;
	}

	public void addDrug(Drugs drug) {
		if(!drugs.contains(drug)) {
			drugs.add(drug);
		}
	}
	
	public void removeDrug(Drugs drug) {
		if(drugs.contains(drug)) {
			drugs.remove(drug);
		}
	}
}
