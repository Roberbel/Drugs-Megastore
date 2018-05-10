package pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.*;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import xml.utils.SQLDateAdapter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "deliveries")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"client", "transactionDate" ,"packages" })
public class Delivery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2324788895880344002L;
	
	@Id
	@GeneratedValue(generator = "deliveries")
	@TableGenerator(name="deliveries", table="sqlite_sequence",
    pkColumnName="name", valueColumnName="seq", pkColumnValue="deliveries")
	
	@XmlAttribute
	private Integer id;
	
	@XmlAttribute
	@Column (name = "selling_price")
	private Integer sellingPrice;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	@XmlElement
	private Client client;
	
	@XmlElement
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	@Column (name = "transaction_date")
	private Date transactionDate;
	
	@XmlAttribute
	private boolean sent;


	@XmlElement(name = "package")
	@XmlElementWrapper(name = "packages")
	@OneToMany(mappedBy = "delivery", fetch = FetchType.LAZY)
	private List<Packaged> packages;
	

	public Delivery() {
		super();
		packages = new ArrayList<Packaged>();
		sellingPrice = 100;
		transactionDate = new Date(System.currentTimeMillis());
	}

	public Delivery(Client client){
		
		this.client = client;
		this.packages = new ArrayList<Packaged>();
		
	}
	
	
	public Delivery(Integer sellingPrice, Date transactionDate, List<Packaged> packages, Client client) {
		super();
		this.sellingPrice = sellingPrice;
		this.transactionDate = transactionDate;
		this.packages = packages;
		this.client = client;
		sent = false;
	}

	
	public Delivery(Integer sellingPrice, Date transactionDate, List<Packaged> packages, Client client, Boolean delivered) {
		super();
		this.sellingPrice = sellingPrice;
		this.transactionDate = transactionDate;
		this.packages = packages;
		this.client = client;
		this.sent = delivered;
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
		return "Id :" + id +  ", client :" + client.getName()  + ", date :" + transactionDate;
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
	
	public boolean addPackaged(Packaged packaged) {
		if (!packages.contains(packaged)) {
			this.packages.add(packaged);
			return true;
		}else {
			
			return false;
			
		}
	}
	
	public int positionPackaged(Packaged packaged){
		
		return packages.indexOf(packaged);
		
	}
	
	public Packaged getPackage(int pos){
		
		return packages.get(pos);
		
	}


	public void removePackaged(Packaged packaged) {
		if (packages.contains(packaged)) {
			this.packages.remove(packaged);
		}
	}
	
	public boolean isSent() {
		return sent;
	}
	
	public void setSent(boolean delivered) {
		this.sent = delivered;
	}



}
