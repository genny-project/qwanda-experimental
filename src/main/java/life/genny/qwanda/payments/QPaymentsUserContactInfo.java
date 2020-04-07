package life.genny.qwanda.payments;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class QPaymentsUserContactInfo implements Serializable {
	
	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;

	@Expose
	 String email;
	
	@Expose
	 String mobile;

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QPaymentsContactInfo [email=" + email + ", mobile=" + mobile + "]";
	}
	

	/**
	 * 
	 */
	public QPaymentsUserContactInfo() {
		super();
	}

	public QPaymentsUserContactInfo(String email, String mobile) {
		super();
		this.email = email;
		this.mobile = mobile;
	}

	public QPaymentsUserContactInfo(String email) {
		super();
		
		if(email != null && !email.trim().isEmpty()) {
			this.email = email;
		} else {
			throw new IllegalArgumentException("Email cannot be empty");
		}
		
	}


}
