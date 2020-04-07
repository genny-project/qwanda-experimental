package life.genny.qwanda.payments;

import com.google.gson.annotations.Expose;

public class QPaymentsUser {
	
	@Expose
	 String id;
	
	@Expose
	 QPaymentsUserInfo personalInfo;
	
	@Expose
	 QPaymentsUserContactInfo contactInfo;
	
	@Expose
	 QPaymentsLocationInfo location;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return the personalInfo
	 */
	public QPaymentsUserInfo getPersonalInfo() {
		return personalInfo;
	}

	/**
	 * @param personalInfo the personalInfo to set
	 */
	public void setPersonalInfo(QPaymentsUserInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	/**
	 * @return the contactInfo
	 */
	public QPaymentsUserContactInfo getContactInfo() {
		return contactInfo;
	}

	/**
	 * @param contactInfo the contactInfo to set
	 */
	public void setContactInfo(QPaymentsUserContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

	/**
	 * @return the location
	 */
	public QPaymentsLocationInfo getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(QPaymentsLocationInfo location) {
		this.location = location;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QPaymentsUser [id=" + id + ", personalInfo=" + personalInfo + ", contactInfo=" + contactInfo
				+ ", location=" + location + "]";
	}

	/**
	 * 
	 * @param id
	 * @param personalInfo
	 * @param contactInfo
	 * @param location
	 * This constructor is used when payments-user is created
	 */
	public QPaymentsUser(String id, QPaymentsUserInfo personalInfo, QPaymentsUserContactInfo contactInfo,
			QPaymentsLocationInfo location) {
		super();
		
		if(id != null && !id.trim().isEmpty()) {
			this.id = id;
		} else {
			throw new IllegalArgumentException("Payment user ID cannot be empty");
		}
		
		if(personalInfo != null) {
			this.personalInfo = personalInfo;
		} else {
			throw new IllegalArgumentException("Missing first name/last name/DOB in user profile");
		}
		
		if(contactInfo != null) {
			this.contactInfo = contactInfo;
		} else {
			throw new IllegalArgumentException("Missing email ID");
		}
		
		if(location != null) {
			this.location = location;
		} else {
			throw new IllegalArgumentException("Missing address line 1/city/state/country/postcode in address");
		}
		
	}

	public QPaymentsUser() {
		super();
	}

	/**
	 * @param id
	 * This constructor is used when payments-company is used
	 */
	public QPaymentsUser(String id) {
		super();
		
		if(id != null && !id.trim().isEmpty()) {
			this.id = id;
		} else {
			throw new IllegalArgumentException("Payments user ID cannot be empty");
		}
	}
	
	

}
