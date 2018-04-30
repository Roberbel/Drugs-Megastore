package pojos;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "employee")
public class Employee extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1163919074127055299L;

	@Id
	@GeneratedValue(generator = "employee")
	@TableGenerator(name="employee", table="sqlite_sequence",
    pkColumnName="name", valueColumnName="seq", pkColumnValue="employee")
	private Integer id;
	private String name;
	private float salary;
	private Integer phone;
	private String position;
	private boolean isAdmin;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name = "warehouse_id")
	private Warehouse warehouse;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] photo;

	public Employee() {
		name = "Tzeench";
		salary = 10000000;/*we better pay well the God of change and manipulation*/
		isAdmin = true;
		position = "Scheemer";
		username = (name+(int)(Math.random()*1000));
		password = ("SoloElCambioEsConstante");
		phone = 9;
	}

	public Employee(Integer id,String name, float salary, Integer phone, String position, byte[] photo,
			String username, String password, boolean admin) {
		
		super(username, password, id);
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.phone = phone;
		this.position = position;
		this.photo = photo;
		this.isAdmin = admin;
	}

	public Employee(String name, float salary, Integer phone, String position, Warehouse warehouseId, byte[] photo,
			String userName, String password, boolean admin) {
		super(userName, password);
		this.name = name;
		this.salary = salary;
		this.phone = phone;
		this.position = position;
		this.warehouse = warehouseId;
		this.photo = photo;
		this.isAdmin = admin;
	}

	public Employee(Integer id, String name, float salary, Integer phone, String position, Warehouse warehouseId,
			byte[] photo, String username, String password, boolean admin) {
		
		super(username, password, id);
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.phone = phone;
		this.position = position;
		this.warehouse = warehouseId;
		this.photo = photo;
		this.isAdmin = admin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((super.getId() == null) ? 0 : super.getId().hashCode());
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
		Employee other = (Employee) obj;
		if (super.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!super.getId().equals(other.getId()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", phone=" + phone
				+ ", position=" + position + ", warehouseId=" + warehouse + ", username = "+ username + ", password = "+password+"]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		super.setId(id);
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
}
