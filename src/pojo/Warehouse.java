package pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class Warehouse implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer pc;
	private String country;
	private String adress;
	private Integer phone;
	private List<Employee> employees;
	
	
	public Warehouse() {
		super();
		this.employees = new ArrayList<Employee>();
	}
	public Warehouse(Integer id, Integer pc, String country,)
	
}
