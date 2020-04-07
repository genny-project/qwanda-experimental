package life.genny.qwanda.payments;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class QPaymentAuthorityForBankAccount implements Serializable{

	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;
	
	@Expose
	 QPaymentMethod account;
	
	@Expose
	 Double amount;

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
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QPaymentAuthorityForBankAccount [account=" + account + ", amount=" + amount + "]";
	}

	public QPaymentAuthorityForBankAccount(QPaymentMethod account, Double amount) {
		super();
			
		if(amount != null && amount > 0.0) {
			this.amount = amount;
		} else {
			throw new IllegalArgumentException("Item amount cannot be empty");
		}
		
		this.account = account;
		
	}
	
}
