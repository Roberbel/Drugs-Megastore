package pojos;

import java.io.Serializable;

public class DeliveryAssociationId implements Serializable{

	private static final long serialVersionUID = 3508657586348971288L;

	private Integer drugId;
	private Integer deliveryId;
	
	public int hashCode() {
		return (int) (drugId+deliveryId);
	}
	
	public boolean equals(Object o) {
		
		if(o instanceof DeliveryAssociationId) {
			
			DeliveryAssociationId otherId = (DeliveryAssociationId) o;
			return(otherId.deliveryId == this.deliveryId) && (otherId.drugId == this.drugId);
		
		}
		return false;
		
	}
}
