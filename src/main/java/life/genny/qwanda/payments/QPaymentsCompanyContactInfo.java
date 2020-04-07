package life.genny.qwanda.payments;

import com.google.gson.annotations.Expose;

public class QPaymentsCompanyContactInfo {
	
	/**
	 * Company landline number
	 */
	@Expose
	 String phone;

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 
	 */
	public QPaymentsCompanyContactInfo() {
		super();
	}

	/**
	 * @param phone
	 */
	public QPaymentsCompanyContactInfo(String phone) {
		super();
		this.phone = phone;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QPaymentsCompanyContactInfo [phone=" + phone + "]";
	}


}
