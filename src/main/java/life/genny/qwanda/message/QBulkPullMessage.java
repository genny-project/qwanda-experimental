package life.genny.qwanda.message;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class QBulkPullMessage implements Serializable {

	
	  /**
	 * 
	 */
	 static final long serialVersionUID = 1L;

	@Expose
	   String token;
	  
	  @Expose
	   String data_type=	 QBulkPullMessage.class.getSimpleName();

	  
	  @Expose
	   String pullUrl;
	  
	  @Expose
	   String[] recipientCodeArray;

	
	public QBulkPullMessage(final String pullUrl){
		this.pullUrl = pullUrl;
	}


	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}


	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}


	/**
	 * @return the data_type
	 */
	public String getData_type() {
		return data_type;
	}


	/**
	 * @param data_type the data_type to set
	 */
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}


	/**
	 * @return the pullUrl
	 */
	public String getPullUrl() {
		return pullUrl;
	}


	/**
	 * @param pullUrl the pullUrl to set
	 */
	public void setPullUrl(String pullUrl) {
		this.pullUrl = pullUrl;
	}


	/**
	 * @return the recipientCodeArray
	 */
	public String[] getRecipientCodeArray() {
		return recipientCodeArray;
	}


	/**
	 * @param recipientCodeArray the recipientCodeArray to set
	 */
	public void setRecipientCodeArray(String[] recipientCodeArray) {
		this.recipientCodeArray = recipientCodeArray;
	}
	


	
	
}
