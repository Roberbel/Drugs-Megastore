package pojos;

import java.io.Serializable;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.sql.Date;

@Entity
@Table(name = "arrival")
public class Arrival implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2841430439397985029L;
	
	private Integer arrivalId;
	private Integer buyingPrice;
	private Date date;
	
	@ManyToOne
	private Provider provider;
	
	@OneToMany(mappedBy = "drug")
	private List<Drug> drugs;

	public Arrival() {
		super();
		drugs = new ArrayList<Drug>();
	}

	
	
	public Arrival(Integer buyingPrice, Date date, Provider provider, List<Drug> drugs) {
		super();
		this.buyingPrice = buyingPrice;
		this.date = date;
		this.provider = provider;
		this.drugs = drugs;
	}



	public Arrival(Integer arrivalId, Integer buyingPrice, Date date, Provider provider,
			List<Drug> drugs) {
		super();
		this.arrivalId = arrivalId;
		this.buyingPrice = buyingPrice;
		this.date = date;
		this.provider = provider;
		this.drugs = drugs;
	}
	public Arrival(Integer arrivalId, Integer buyingPrice, Date date,  Provider provider) {
		super();
		this.arrivalId = arrivalId;
		this.buyingPrice = buyingPrice;
		this.date = date;
		this.provider = provider;
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
		Arrival other = (Arrival) obj;
		if (arrivalId == null) {
			if (other.arrivalId != null)
				return false;
		} else if (!arrivalId.equals(other.arrivalId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Arrivals [arrivalId=" + arrivalId + ", buyingPrice=" + buyingPrice + ", date=" + date + ", provider=" + provider + ", drugs=" + drugs + "]";
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

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public List<Drug> getDrugs() {
		return drugs;
	}

	public void setDrugs(List<Drug> drugs) {
		this.drugs = drugs;
	}

	public void addDrug(Drug drug) {
		if(!drugs.contains(drug)) {
			drugs.add(drug);
		}
	}
	
	public void removeDrug(Drug drug) {
		if(drugs.contains(drug)) {
			drugs.remove(drug);
		}
	}
}