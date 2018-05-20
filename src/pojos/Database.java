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
@XmlType(propOrder = {"drugs", "clients", "providers", "deliveries", "arrivals", "packaged", "arrives", 
		"corridors", "warehouses" })
public class Database {

	@XmlElement(name = "Arrival")
	@XmlElementWrapper(name = "Arrivals")
	private List<Arrival> arrivals;
	
	@XmlElement(name = "Arrive")
	@XmlElementWrapper(name = "Arrives")
	private List<Arrives> arrives;
	
	@XmlElement(name = "Client")
	@XmlElementWrapper(name = "Clients")
	private List<Client> clients;
	
	@XmlElement(name = "Corridor")
	@XmlElementWrapper(name = "Corridors")
	private List<Corridor> corridors;
	
	@XmlElement(name = "Delivery")
	@XmlElementWrapper(name = "Deliveries")
	private List<Delivery> deliveries;
	
	@XmlElement(name = "Drug")
	@XmlElementWrapper(name = "Drugs")
	private List<Drug> drugs;
	
	@XmlElement(name = "Employee")
	@XmlElementWrapper(name = "Employees")
	private List<Employee> employees;
	
	@XmlElement(name = "Package")
	@XmlElementWrapper(name = "Packages")
	private List<Packaged> packaged;
	
	@XmlElement(name = "Provider")
	@XmlElementWrapper(name = "Providers")
	private List<Provider> providers;
	
	@XmlElement(name = "Warehouse")
	@XmlElementWrapper(name = "Warehouses")
	private List<Warehouse> warehouses;
	
	public Database() {
		
		arrivals = new ArrayList<Arrival>();
		arrives = new ArrayList<Arrives>();
		clients = new ArrayList<Client>();
		corridors = new ArrayList<Corridor>();
		deliveries = new ArrayList<Delivery>();
		drugs = new ArrayList<Drug>();
		employees = new ArrayList<Employee>();
		packaged = new ArrayList<Packaged>();
		providers = new ArrayList<Provider>();
		warehouses = new ArrayList<Warehouse>();
		
	}
	
	public Database(List<Arrival> arrivals,  List<Arrives> arrives, List<Client> clients, List<Corridor> corridors,
			List<Delivery> deliveries, List<Drug> drugs, List<Employee> employees, List<Packaged> packaged, 
			List<Provider> providers, List<Warehouse> warehouses) {
		
		this.arrivals = arrivals;
		this.arrives = arrives;
		this.clients = clients;
		this.corridors = corridors;
		this.deliveries = deliveries;
		this.drugs = drugs;
		this.employees = employees;
		this.packaged = packaged;
		this.providers = providers;
		this.warehouses = warehouses;
		
	}
	
	public List<Arrival> getArrivals() {
		return arrivals;
	}

	public void setArrivals(List<Arrival> arrivals) {
		this.arrivals = arrivals;
	}

	public List<Arrives> getArrives() {
		return arrives;
	}

	public void setArrives(List<Arrives> arrives) {
		this.arrives = arrives;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public List<Corridor> getCorridors() {
		return corridors;
	}

	public void setCorridors(List<Corridor> corridors) {
		this.corridors = corridors;
	}

	public List<Delivery> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(List<Delivery> deliveries) {
		this.deliveries = deliveries;
	}

	public List<Drug> getDrugs() {
		return drugs;
	}

	public void setDrugs(List<Drug> drugs) {
		this.drugs = drugs;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<Packaged> getPackaged() {
		return packaged;
	}

	public void setPackaged(List<Packaged> packaged) {
		this.packaged = packaged;
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
