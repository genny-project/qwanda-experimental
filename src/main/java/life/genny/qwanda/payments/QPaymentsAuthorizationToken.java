package life.genny.qwanda.payments;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class QPaymentsAuthorizationToken implements Serializable {

	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;
	
	public enum AuthorizationPaymentType {
		card,
		bank
	}
	
	@Expose
	 AuthorizationPaymentType type;
	
	@Expose
	 QPaymentsUser user;

	@Expose
	 String token;

	/**
	 * @return the type
	 */
	public AuthorizationPaymentType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(AuthorizationPaymentType type) {
		this.type = type;
	}

	/**
	 * @return the user
	 */
	public QPaymentsUser getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(QPaymentsUser user) {
		this.user = user;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QPaymentsAuthorizationToken [type=" + type + ", user=" + user + ", token=" + token + "]";
	}

	public QPaymentsAuthorizationToken(AuthorizationPaymentType type, QPaymentsUser user) {
		super();
		
		if(type != null) {
			this.type = type;
		} else {
			throw new IllegalArgumentException("Type of payment cannot be empty");
		}
		
		this.user = user;
	}
	
	

}
