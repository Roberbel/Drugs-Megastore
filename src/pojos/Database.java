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

}
