package pojos;

import java.io.Serializable;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import xml.utils.SQLDateAdapter;

import java.sql.Date;

@Entity
@Table(name = "arrivals")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Arrivals")
@XmlType(propOrder = {"date", "arrives"})
public class Arrival implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2841430439397985029L;
	
	@Id
	@GeneratedValue(generator = "arrivals")
	@TableGenerator(name="arrivals", table="sqlite_sequence",
    pkColumnName="name", valueColumnName="seq", pkColumnValue="arrivals")
	@XmlAttribute
	@Column(name = "transaction_id")
	private Integer arrivalId;
	
	@Column(name = "buying_price")
	@XmlAttribute
	private Integer buyingPrice;
	
	@XmlAttribute
	private boolean received;

	@Column(name = "transaction_date") 
	@XmlElement
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date date;
	
	@ManyToOne
	@JoinColumn(name = "provider_id")
	@XmlTransient
	private Provider provider;
	
	@OneToMany(mappedBy = "arrival", fetch = FetchType.LAZY)
	@XmlElement(name = "Arrive")
	@XmlElementWrapper(name = "Arrives")
	private List<Arrives> arrives;

	public Arrival() {
		super();
		arrives = new ArrayList<Arrives>();
		buyingPrice = 100;
		date = new Date(System.currentTimeMillis());
	}

	
	
	public Arrival(Integer buyingPrice, Date date, Provider provider, List<Arrives> arrives) {
		super();
		this.buyingPrice = buyingPrice;
		this.date = date;
		this.provider = provider;
		this.arrives = arrives;
		received = false;
	}

	
	public Arrival(Integer buyingPrice, Date date, Provider provider, List<Arrives> arrives, Boolean arrived) {
		super();
		this.buyingPrice = buyingPrice;
		this.date = date;
		this.provider = provider;
		this.arrives = arrives;
		this.received = arrived;
	}

	public Arrival(Integer arrivalId, Integer buyingPrice, Date date, Provider provider,
			List<Arrives> arrives, Boolean arrived) {
		super();
		this.arrivalId = arrivalId;
		this.buyingPrice = buyingPrice;
		this.date = date;
		this.provider = provider;
		this.arrives = arrives;
		this.received = arrived;
	}
	public Arrival(Integer arrivalId, Integer buyingPrice, Date date,  Provider provider, Boolean arrived) {
		super();
		this.arrivalId = arrivalId;
		this.buyingPrice = buyingPrice;
		this.date = date;
		this.provider = provider;
		this.received = arrived; 
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
		return "Id :" + arrivalId +  ", provider :" + provider.getName()  + ", date :" + date;
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

	public List<Arrives> getArrives() {
		return arrives;
	}

	
	public boolean isReceived() {
		return received;
	}

	public void setReceived(boolean received) {
		this.received = received;
	}
	
	public void setArrives(List<Arrives> arrives) {
		this.arrives = arrives;
	}

	public void addArrive(Drug drug, Integer amount) {
		Arrives arrive = new Arrives(drug, this, amount);
		if(!arrives.contains(arrive)) {
			arrives.add(arrive);
		}
	}
	
	public void removeArrive(Arrives arrive) {
		if(arrives.contains(arrive)) {
			arrives.remove(arrive);
		}
	}
}