package co.com.neoris.api.entity;

import java.util.List;

public class ResponseReportMovement {
	
	private String message;
	
	private List<ReportMovement> movements;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ReportMovement> getMovements() {
		return movements;
	}

	public void setMovements(List<ReportMovement> movements) {
		this.movements = movements;
	}

	@Override
	public String toString() {
		return "ResponseReportMovement [message=" + message + ", movements=" + movements + "]";
	}
	
	

}
