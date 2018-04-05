package pojos;

import java.io.Serializable;
import java.util.*;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "drug")
public class Drug implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4590682648561951620L;
	@Id
	private Integer id;
	private String name;
	private Integer stock;
	private Integer sellingPrice;
	@Column(name = "active_principle")
	private String activePrinciple;
	@ManyToOne
	@JoinColumn(name="corridor_id")
	@Basic(fetch = FetchType.LAZY)
	private Corridor corridor;
	@ManyToMany(mappedBy = "drugs")
	@Basic(fetch = FetchType.LAZY)
	private List<Delivery> deliveries;
	@ManyToMany(mappedBy = "drugs")
	@Basic(fetch = FetchType.LAZY)
	private List<Arrival> arrivals;
	@Lob
	private byte[] photo;

	public Drug() {
		super();
		deliveries = new ArrayList<Delivery>();
		arrivals = new ArrayList<Arrival>();
	}
	

	public Drug(String name, Integer stock, Integer sellingPrice, String activePrinciple, Corridor corridor,
			List<Delivery> deliveries, List<Arrival> arrivals, byte[] photo) {
		super();
		this.name = name;
		this.stock = stock;
		this.sellingPrice = sellingPrice;
		this.activePrinciple = activePrinciple;
		this.corridor = corridor;
		this.deliveries = deliveries;
		this.arrivals = arrivals;
		this.photo = photo;
	}



	public Drug(Integer id, String name, Integer stock, Integer sellingPrice, Corridor corridor,
			List<Delivery> deliveries, List<Arrival> arrivals, byte[] photo) {
		super();
		this.id = id;
		this.name = name;
		this.stock = stock;
		this.sellingPrice = sellingPrice;
		this.corridor = corridor;
		this.deliveries = deliveries;
		this.arrivals = arrivals;
		this.photo=photo;
	}
	
	public Drug(Integer id, String name, Integer stock, Integer sellingPrice, Corridor corridor,
			byte[] photo) {
		super();
		this.id = id;
		this.name = name;
		this.stock = stock;
		this.sellingPrice = sellingPrice;
		this.corridor = corridor;
		this.photo=photo;
	}


	public Drug(Integer id, String name, Integer stock, Integer sellingPrice, String activePrinciple,
			Corridor corridor, List<Delivery> deliveries, List<Arrival> arrivals, byte[]photo) {
		super();
		this.id = id;
		this.name = name;
		this.stock = stock;
		this.sellingPrice = sellingPrice;
		this.activePrinciple = activePrinciple;
		this.corridor = corridor;
		this.deliveries = deliveries;
		this.arrivals = arrivals;
		this.photo=photo;
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
		Drug other = (Drug) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Drugs [id=" + id + ", name=" + name + ", stock=" + stock + ", sellingPrice=" + sellingPrice
				+ ", activePrinciple=" + activePrinciple + ", corridor=" + corridor + "]";
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

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Integer sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getActivePrinciple() {
		return activePrinciple;
	}

	public void setActivePrinciple(String activePrinciple) {
		this.activePrinciple = activePrinciple;
	}

	public List<Delivery> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(List<Delivery> deliveries) {
		this.deliveries = deliveries;
	}

	public List<Arrival> getArrivals() {
		return arrivals;
	}

	public void setArrivals(List<Arrival> arrivals) {
		this.arrivals = arrivals;
	}

	public void addDelivery(Delivery delivery) {
		if (!deliveries.contains(delivery)) {
			deliveries.add(delivery);
		}
	}

	public void removeDelivery(Delivery delivery) {
		if (deliveries.contains(delivery)) {
			deliveries.remove(delivery);
		}
	}

	public void addArrival(Arrival arrival) {
		if (!arrivals.contains(arrival)) {
			arrivals.add(arrival);
		}
	}

	public void removeArrival(Arrival arrival) {
		if (arrivals.contains(arrival)) {
			arrivals.remove(arrival);
		}
	}

	public Corridor getCorridor() {
		return corridor;
	}

	public void setCorridor(Corridor corridor) {
		this.corridor = corridor;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
}
