package pojos;

import java.io.Serializable;

public class ArrivalAssociationId implements Serializable{

	private static final long serialVersionUID = 3508657586348971288L;

	private Integer drugId;
	private Integer arrivalId;
	
	public int hashCode() {
		return (int) (drugId+arrivalId);
	}
	
	public boolean equals(Object o) {
		
		if(o instanceof ArrivalAssociationId) {
			
			ArrivalAssociationId otherId = (ArrivalAssociationId) o;
			return(otherId.arrivalId == this.arrivalId) && (otherId.drugId == this.drugId);
		
		}
		return false;
		
	}
}