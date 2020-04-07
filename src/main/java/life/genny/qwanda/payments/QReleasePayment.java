package life.genny.qwanda.payments;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class QReleasePayment implements Serializable{

	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;
	
	@Expose
	 Boolean singleItemDisbursement;
	
	/* disbursement id */
	@Expose
	 String id;

	/**
	 * @return the singleItemDisbursement
	 */
	public Boolean getSingleItemDisbursement() {
		return singleItemDisbursement;
	}

	/**
	 * @param singleItemDisbursement the singleItemDisbursement to set
	 */
	public void setSingleItemDisbursement(Boolean singleItemDisbursement) {
		this.singleItemDisbursement = singleItemDisbursement;
	}

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QReleasePayment [singleItemDisbursement=" + singleItemDisbursement + ", id=" + id + "]";
	}

	public QReleasePayment(Boolean singleItemDisbursement) {
		super();
		this.singleItemDisbursement = singleItemDisbursement;
	}
	
	
	
	
}
