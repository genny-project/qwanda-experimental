package life.genny.qwanda.payments.assembly;

import java.io.Serializable;
import java.util.Arrays;

import com.google.gson.annotations.Expose;

import life.genny.qwanda.payments.QPaymentsUserContactInfo;

public class QPaymentsAssemblyUserResponse implements Serializable {
	
	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;

	@Expose
	 String id;
	
	@Expose
	 QPaymentsAssemblyUserInfoResponse personalInfo;
	
	@Expose
	 QPaymentsUserContactInfo contactInfo;
	
	@Expose
	 QPaymentsAssemblyKYC kyc;
	
	@Expose
	 String[] roles;
	
	@Expose
	 String location;
	
	@Expose
	 String createdAt;
	
	@Expose
	 String updatedAt;
	
	@Expose
	 String payoutAccount;

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
	public QPaymentsAssemblyUserInfoResponse getPersonalInfo() {
		return personalInfo;
	}

	/**
	 * @param personalInfo the personalInfo to set
	 */
	public void setPersonalInfo(QPaymentsAssemblyUserInfoResponse personalInfo) {
		this.personalInfo = personalInfo;
	}


	/**
	 * @return the createdAt
	 */
	public String getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the updatedAt
	 */
	public String getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
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
	 * @return the kyc
	 */
	public QPaymentsAssemblyKYC getKyc() {
		return kyc;
	}

	/**
	 * @param kyc the kyc to set
	 */
	public void setKyc(QPaymentsAssemblyKYC kyc) {
		this.kyc = kyc;
	}

	/**
	 * @return the roles
	 */
	public String[] getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(String[] roles) {
		this.roles = roles;
	}
	

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	

	/**
	 * @return the payoutAccount
	 */
	public String getPayoutAccount() {
		return payoutAccount;
	}

	/**
	 * @param payoutAccount the payoutAccount to set
	 */
	public void setPayoutAccount(String payoutAccount) {
		this.payoutAccount = payoutAccount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QPaymentsAssemblyUserResponse [id=" + id + ", personalInfo=" + personalInfo + ", contactInfo="
				+ contactInfo + ", kyc=" + kyc + ", roles=" + Arrays.toString(roles) + ", location=" + location
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", payoutAccount=" + payoutAccount + "]";
	}



}
