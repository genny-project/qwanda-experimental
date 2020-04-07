package life.genny.qwanda;

import java.util.Map;

public class PaymentsResponse {
	
	 Boolean isSuccess;
	 String message;
	 Map<String, String> responseMap;
	/**
	 * @return the isSuccess
	 */
	public Boolean getIsSuccess() {
		return isSuccess;
	}
	/**
	 * @param isSuccess the isSuccess to set
	 */
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the responseMap
	 */
	public Map<String, String> getResponseMap() {
		return responseMap;
	}
	/**
	 * @param responseMap the responseMap to set
	 */
	public void setResponseMap(Map<String, String> responseMap) {
		this.responseMap = responseMap;
	}
	
	
	
	

}
