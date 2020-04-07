package life.genny.qwanda.payments;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class QPaymentsDisbursement implements Serializable {
	
	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;

	@Expose
	 String disbursementId;
	
	@Expose
	 String batchId;
	
	@Expose
	 QPaymentMethod account;

	/**
	 * @return the disbursementId
	 */
	public String getDisbursementId() {
		return disbursementId;
	}

	/**
	 * @param disbursementId the disbursementId to set
	 */
	public void setDisbursementId(String disbursementId) {
		this.disbursementId = disbursementId;
	}

	/**
	 * @return the batchId
	 */
	public String getBatchId() {
		return batchId;
	}

	/**
	 * @param batchId the batchId to set
	 */
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	

	/**
	 * @return the account
	 */
	public QPaymentMethod getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(QPaymentMethod account) {
		this.account = account;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QPaymentsDisbursement [disbursementId=" + disbursementId + ", batchId=" + batchId + ", account="
				+ account + "]";
	}

	public QPaymentsDisbursement(QPaymentMethod account) {
		super();
		this.account = account;
	}
	

}
