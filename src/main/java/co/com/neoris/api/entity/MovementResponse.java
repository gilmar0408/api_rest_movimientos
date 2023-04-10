package co.com.neoris.api.entity;

public class MovementResponse {
	
	
	private String message;
	
	private Movement movement;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Movement getMovement() {
		return movement;
	}

	public void setMovement(Movement movement) {
		this.movement = movement;
	}

	@Override
	public String toString() {
		return "MovementResponse [message=" + message + ", movement=" + movement + "]";
	}
	
	

}
