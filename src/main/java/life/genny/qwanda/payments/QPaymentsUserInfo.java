package life.genny.qwanda.payments;

import java.io.Serializable;
import java.time.LocalDate;

import com.google.gson.annotations.Expose;

/* For personal info */
public class QPaymentsUserInfo implements Serializable{

	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;
	@Expose
	 String firstName;
	
	@Expose
	 String lastName;
	
	@Expose
	 LocalDate dob;
	
	@Expose
	 String governmentNumber;
	
	@Expose
	 String fullName;


	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	/**
	 * @return the dob
	 */
	public LocalDate getDob() {
		return dob;
	}

	/**
	 * @param dob the dob to set
	 */
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	/**
	 * @return the governmentNumber
	 */
	public String getGovernmentNumber() {
		return governmentNumber;
	}

	/**
	 * @param governmentNumber the governmentNumber to set
	 */
	public void setGovernmentNumber(String governmentNumber) {
		this.governmentNumber = governmentNumber;
	}
	
	
	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public QPaymentsUserInfo() {
		super();
	}

	public QPaymentsUserInfo(String firstName, String lastName, LocalDate dob) {
		super();
		if(firstName != null && !firstName.trim().isEmpty()) {
			this.firstName = firstName;
		} else {
			throw new IllegalArgumentException("First name cannot be empty");
		}
		
		if(lastName != null && !lastName.trim().isEmpty()) {
			this.lastName = lastName;
		} else {
			throw new IllegalArgumentException("Last name cannot be empty");
		}
		
		if(dob != null) {
			this.dob = dob;
		} else {
			throw new IllegalArgumentException("DOB cannot be empty");
		}
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QPaymentsUserInfo [firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob
				+ ", governmentNumber=" + governmentNumber + ", fullName=" + fullName + "]";
	}


}
