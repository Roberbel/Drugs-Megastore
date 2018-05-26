package pojos;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Database")
@XmlType(propOrder = {"clients", "providers", "warehouses" })
public class Database {

	@XmlElement(name = "Client")
	@XmlElementWrapper(name = "Clients")
	private List<Client> clients;

	@XmlElement(name = "Provider")
	@XmlElementWrapper(name = "Providers")
	private List<Provider> providers;
	
	@XmlElement(name = "Warehouse")
	@XmlElementWrapper(name = "Warehouses")
	private List<Warehouse> warehouses;
	
	public Database() {

		clients = new ArrayList<Client>();
		providers = new ArrayList<Provider>();
		warehouses = new ArrayList<Warehouse>();
		
	}
	
	public Database(List<Client> clients, List<Provider> providers, List<Warehouse> warehouses) {

		this.clients = clients;
		this.providers = providers;
		this.warehouses = warehouses;
		
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public List<Provider> getProviders() {
		return providers;
	}

	public void setProviders(List<Provider> providers) {
		this.providers = providers;
	}

	public List<Warehouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clients == null) ? 0 : clients.hashCode());
		result = prime * result + ((providers == null) ? 0 : providers.hashCode());
		result = prime * result + ((warehouses == null) ? 0 : warehouses.hashCode());
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
		Database other = (Database) obj;
		if (clients == null) {
			if (other.clients != null)
				return false;
		} else if (!clients.equals(other.clients))
			return false;
		if (providers == null) {
			if (other.providers != null)
				return false;
		} else if (!providers.equals(other.providers))
			return false;
		if (warehouses == null) {
			if (other.warehouses != null)
				return false;
		} else if (!warehouses.equals(other.warehouses))
			return false;
		return true;
	}
	
	

}
