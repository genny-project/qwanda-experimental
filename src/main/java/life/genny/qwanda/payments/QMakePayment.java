package life.genny.qwanda.payments;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class QMakePayment implements Serializable {

	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;

	/* Item id */
	@Expose
	 String id;
	
	@Expose
	 QPaymentMethod account;
	
	@Expose
	 String ipAddress;
	
	@Expose
	 String deviceID;

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

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return the deviceID
	 */
	public String getDeviceID() {
		return deviceID;
	}

	/**
	 * @param deviceID the deviceID to set
	 */
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QPaymentsMakePayment [id=" + id + ", account=" + account + ", ipAddress=" + ipAddress + ", deviceID="
				+ deviceID + "]";
	}

	public QMakePayment(String id, QPaymentMethod account, String ipAddress, String deviceID) {
		super();
		
		if(id != null && !id.trim().isEmpty()) {
			this.id = id;
		} else {
			throw new IllegalArgumentException("Something went wrong when payment item was created. Payment Item Id cannot be empty");
		}
		
		if(account != null) {
			this.account = account;
		} else {
			throw new IllegalArgumentException("Payment method cannot be empty");
		}
		
		if(ipAddress != null && !ipAddress.trim().isEmpty()) {
			this.ipAddress = ipAddress;
		} else {
			throw new IllegalArgumentException("IP Address cannot be empty");
		}
		
		if(deviceID != null && !deviceID.trim().isEmpty()) {
			this.deviceID = deviceID;
		} else {
			throw new IllegalArgumentException("Device ID cannot be empty");
		}
	
	}
	
	
	
}
