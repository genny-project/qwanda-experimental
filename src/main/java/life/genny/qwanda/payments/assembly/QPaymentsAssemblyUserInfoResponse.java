package life.genny.qwanda.payments.assembly;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

/* For personal info */
	public class QPaymentsAssemblyUserInfoResponse implements Serializable {

		/**
		 * 
		 */
		 static final long serialVersionUID = 1L;
		@Expose
		 String firstName;
		
		@Expose
		 String lastName;
		
		@Expose
		 String dob;
		
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
		public String getDob() {
			return dob;
		}

		/**
		 * @param dob the dob to set
		 */
		public void setDob(String dob) {
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

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "QPaymentsAssemblyUserInfoResponse [firstName=" + firstName + ", lastName=" + lastName + ", dob="
					+ dob + ", governmentNumber=" + governmentNumber + ", fullName=" + fullName + "]";
		}
		
		

}
