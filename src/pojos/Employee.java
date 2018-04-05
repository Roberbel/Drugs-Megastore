package pojos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1163919074127055299L;

	private String name;
	private float salary;
	private Integer phone;
	private String position;
	private boolean isAdmin;
	@ManyToOne
	private Warehouse warehouse;
	@Lob
	private byte[] photo;

	public Employee() {
		super();
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
			byte[] photo, String userName, String password, boolean admin) {
		super(id, userName, password);
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
		return "Employee [id=" + super.getId() + ", name=" + name + ", salary=" + salary + ", phone=" + phone
				+ ", position=" + position + ", warehouseId=" + warehouse + "]";
	}

	public Integer getId() {
		return super.getId();
	}

	public void setId(Integer id) {
		super.setId(id);
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

	public Warehouse getWarehouseId() {
		return warehouse;
	}

	public void setWarehouseId(Warehouse warehouseId) {
		this.warehouse = warehouseId;
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
