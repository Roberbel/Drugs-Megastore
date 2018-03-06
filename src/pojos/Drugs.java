package pojos;

import java.io.Serializable;
import java.util.*;

public class Drugs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4590682648561951620L;
	private Integer id;
	private String name;
	private Integer stock;
	private Integer sellingPrice;
	private String activePrinciple;
	private Corridors corridor;
	private List<Deliveries> deliveries;
	private List<Arrivals> arrivals;
	private byte[] photo;

	public Drugs() {
		super();
		deliveries = new ArrayList<Deliveries>();
		arrivals = new ArrayList<Arrivals>();
	}
	

	public Drugs(String name, Integer stock, Integer sellingPrice, String activePrinciple, Corridors corridor,
			List<Deliveries> deliveries, List<Arrivals> arrivals, byte[] photo) {
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



	public Drugs(Integer id, String name, Integer stock, Integer sellingPrice, Corridors corridor,
			List<Deliveries> deliveries, List<Arrivals> arrivals, byte[] photo) {
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

	public Drugs(Integer id, String name, Integer stock, Integer sellingPrice, String activePrinciple,
			Corridors corridor, List<Deliveries> deliveries, List<Arrivals> arrivals, byte[]photo) {
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
		Drugs other = (Drugs) obj;
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

	public List<Deliveries> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(List<Deliveries> deliveries) {
		this.deliveries = deliveries;
	}

	public List<Arrivals> getArrivals() {
		return arrivals;
	}

	public void setArrivals(List<Arrivals> arrivals) {
		this.arrivals = arrivals;
	}

	public void addDelivery(Deliveries delivery) {
		if (!deliveries.contains(delivery)) {
			deliveries.add(delivery);
		}
	}

	public void removeDelivery(Deliveries delivery) {
		if (deliveries.contains(delivery)) {
			deliveries.remove(delivery);
		}
	}

	public void addArrival(Arrivals arrival) {
		if (!arrivals.contains(arrival)) {
			arrivals.add(arrival);
		}
	}

	public void removeArrival(Arrivals arrival) {
		if (arrivals.contains(arrival)) {
			arrivals.remove(arrival);
		}
	}

	public Corridors getCorridor() {
		return corridor;
	}

	public void setCorridor(Corridors corridor) {
		this.corridor = corridor;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
}
