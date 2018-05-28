package pojos;

import java.io.Serializable;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "drug")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Drug")
@XmlType
public class Drug implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4590682648561951620L;
	
	@Id
	@GeneratedValue(generator = "drug")
	@TableGenerator(name="drug", table="sqlite_sequence",
    pkColumnName="name", valueColumnName="seq", pkColumnValue="drug")
	@XmlAttribute
	private Integer id;
	
	@XmlAttribute
	private String name;
	@XmlAttribute
	private Integer stock;
	@Column (name = "selling_price")
	@XmlAttribute
	private Integer sellingPrice;
	
	@XmlAttribute
	@Column(name = "active_principle")
	private String activePrinciple;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="corridor_id")
	@XmlTransient
	private Corridor corridor;
	
	@XmlTransient
	@OneToMany(mappedBy = "drug", fetch = FetchType.LAZY)
	private List<Packaged> packaged;
	
	@XmlTransient
	@OneToMany(mappedBy = "drug", fetch = FetchType.LAZY)
	private List<Arrives> arrives;
	
	@Lob
	@XmlTransient
	private byte[] photo;

	public Drug() {
		super();
		packaged = new ArrayList<Packaged>();
		corridor = new Corridor();
		arrives = new ArrayList<Arrives>();
		name = "Droga"+(int)(Math.random()*10000);
		stock = (int)(Math.random()*100000);
		sellingPrice = (int)(Math.random()*100);
	}
	

	public Drug(String name, Integer stock, Integer sellingPrice, String activePrinciple, Corridor corridor,
			List<Packaged> packaged, List<Arrives> arrives, byte[] photo) {
		super();
		this.name = name;
		this.stock = stock;
		this.sellingPrice = sellingPrice;
		this.activePrinciple = activePrinciple;
		this.corridor = corridor;
		this.packaged = packaged;
		this.arrives = arrives;
		this.photo = photo;
	}
	public Drug(Integer id, String name, Integer stock, Integer sellingPrice, String activePrinciple, Corridor corridor,
		 byte[] photo) {
		super();
		this.name = name;
		this.stock = stock;
		this.sellingPrice = sellingPrice;
		this.activePrinciple = activePrinciple;
		this.corridor = corridor;
		this.photo = photo;
	}


	public Drug(Integer id, String name, Integer stock, Integer sellingPrice, Corridor corridor,
			List<Packaged> deliveries, List<Arrives> arrives, byte[] photo) {
		super();
		this.id = id;
		this.name = name;
		this.stock = stock;
		this.sellingPrice = sellingPrice;
		this.corridor = corridor;
		this.packaged = deliveries;
		this.arrives = arrives;
		this.photo=photo;
	}
	
	public Drug( String name, Integer stock, Integer sellingPrice, String activePrinciple, Corridor corridor,byte[] photo) {
		super();
		this.name = name;
		this.stock = stock;
		this.sellingPrice = sellingPrice;
		this.corridor = corridor;
		this.photo=photo;
	}


	public Drug(Integer id, String name, Integer stock, Integer sellingPrice, String activePrinciple,
			Corridor corridor, List<Packaged> deliveries, List<Arrives> arrives, byte[]photo) {
		super();
		this.id = id;
		this.name = name;
		this.stock = stock;
		this.sellingPrice = sellingPrice;
		this.activePrinciple = activePrinciple;
		this.corridor = corridor;
		this.packaged = deliveries;
		this.arrives = arrives;
		this.photo=photo;
	}
	
	

	public Drug(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
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
		/*return "Drugs [id=" + id + ", name=" + name + ", stock=" + stock + ", sellingPrice=" + sellingPrice
				+ ", activePrinciple=" + activePrinciple + ", corridor=" + corridor + "]";*/
		return this.name;
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

	public List<Packaged> getPackaged() {
		return packaged;
	}

	public void setPackaged(List<Packaged> packaged) {
		this.packaged = packaged;
	}

	public List<Arrives> getArrives() {
		return arrives;
	}

	public void setArrives(List<Arrives> arrives) {
		this.arrives = arrives;
	}

	public void addPackaged(Packaged packaged) {
		if (!this.packaged.contains(packaged)) {
			this.packaged.add(packaged);
		}
	}

	public void removePackaged(Packaged packaged) {
		if (this.packaged.contains(packaged)) {
			this.packaged.remove(packaged);
		}
	}

	public void addArrive(Arrives arrive) {
		if (!arrives.contains(arrive)) {
			arrives.add(arrive);
		}
	}

	public void removeArrive(Arrives arrive) {
		if (arrives.contains(arrive)) {
			arrives.remove(arrive);
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
