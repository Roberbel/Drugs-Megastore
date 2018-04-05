package pojos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2459371836299683662L;

	public enum UserClass {
		EMPLOYEE, ADMIN
	}

	private String userName;
	private String password;
	private UserClass type;
	@Id
	private Integer id;

	public User() {
		super();
	}

	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	public User(int id, String userName, String password) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		
	}

	public User(String userName, String password, UserClass type, Integer id) {
		super();
		this.userName = userName;
		this.password = password;
		this.type = type;
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		User other = (User) obj;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", type=" + type + ", id=" + id + "]";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserClass getType() {
		return type;
	}

	public void setType(UserClass type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/*
	 * public boolean checkPassword() {
	 * 
	 * 
	 * 
	 * }
	 */


}
