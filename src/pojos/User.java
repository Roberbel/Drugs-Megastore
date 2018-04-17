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

	private String username;
	private String password;

	@Id
	private Integer id;

	public User() {
		super();
	}

	public User(String userName, String password) {
		super();
		this.username = userName;
		this.password = password;
	}
	
	public User(int id, String userName, String password) {
		super();
		this.id = id;
		this.username = userName;
		this.password = password;
		
	}

	public User(String userName, String password, Integer id) {
		super();
		this.username = userName;
		this.password = password;
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userName=" + username + ", password=" + password + ", id=" + id + "]";
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
