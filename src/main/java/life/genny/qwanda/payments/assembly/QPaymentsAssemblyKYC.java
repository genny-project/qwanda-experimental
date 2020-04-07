package life.genny.qwanda.payments.assembly;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class QPaymentsAssemblyKYC implements Serializable {
	
	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;

	public enum UserKYCState {
		
		//Waiting for information of the user to fulfil our KYC data requirements
		pending,
		
		//Information received, waiting for Assembly to Approve the KYC
		pending_check,
		
		//Stage 1 of the KYC is approved
		approved_kyc_check,
		
		//Stage 1 and underwriting of the user has been approved.
		approved		
	}
	
	@Expose
	 UserKYCState verificationStatus; 
	
	@Expose
	 Boolean heldState;

	/**
	 * @return the verificationStatus
	 */
	public UserKYCState getVerificationStatus() {
		return verificationStatus;
	}

	/**
	 * @param verificationStatus the verificationStatus to set
	 */
	public void setVerificationStatus(UserKYCState verificationStatus) {
		this.verificationStatus = verificationStatus;
	}

	/**
	 * @return the heldState
	 */
	public Boolean getHeldState() {
		return heldState;
	}

	/**
	 * @param heldState the heldState to set
	 */
	public void setHeldState(Boolean heldState) {
		this.heldState = heldState;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QPaymentsAssemblyKYC [verificationStatus=" + verificationStatus + ", heldState=" + heldState + "]";
	}
	

}
