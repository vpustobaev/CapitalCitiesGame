package by.htp.cities.model;

public class PlayerOperationResponse {

	private String city;
	private PlayerOperation operationResult;

	public String getCity() {
		return city;
	}

	public void setCity(String name) {
		this.city = name;
	}

	public PlayerOperation getOperationResult() {
		return operationResult;
	}

	public void setOperationResult(PlayerOperation operationResult) {
		this.operationResult = operationResult;
	}

}
