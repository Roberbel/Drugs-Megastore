package pojo;

import java.io.Serializable;

public class Client implements Serializable{

		/**
	 * 
	 */
		private static final long serialVersionUID = 2116743967910216027L;
		
		private Integer id;
		private String name;
		private String adress;
		private Integer telephone;
		private String email;
		private String paymentMethod;
		
		@Override
		public String toString() {
			return "Client [id=" + id + ", name=" + name + ", adress=" + adress + ", telephone=" + telephone
					+ ", email=" + email + ", paymentMethod=" + paymentMethod + "]";
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
		public String getAdress() {
			return adress;
		}
		public void setAdress(String adress) {
			this.adress = adress;
		}
		public Integer getTelephone() {
			return telephone;
		}
		public void setTelephone(Integer telephone) {
			this.telephone = telephone;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPaymentMethod() {
			return paymentMethod;
		}
		public void setPaymentMethod(String paymentMethod) {
			this.paymentMethod = paymentMethod;
		}
}
