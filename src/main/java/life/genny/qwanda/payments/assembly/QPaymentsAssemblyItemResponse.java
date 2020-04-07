package life.genny.qwanda.payments.assembly;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class QPaymentsAssemblyItemResponse implements Serializable {

	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;
	
	@Expose
	 String id;
	
	@Expose
	 String description;
	
	@Expose
	 String state;

	@Expose
	 String paymentType;
	
	@Expose
	 Double amount;
	
	@Expose
	 String currency;
	
	@Expose
	 String depositReference;
	
	@Expose
	 QPaymentsAssemblyUser buyer;
	
	@Expose
	 QPaymentsAssemblyUser seller;

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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}

	/**
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
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

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the depositReference
	 */
	public String getDepositReference() {
		return depositReference;
	}

	/**
	 * @param depositReference the depositReference to set
	 */
	public void setDepositReference(String depositReference) {
		this.depositReference = depositReference;
	}

	/**
	 * @return the buyer
	 */
	public QPaymentsAssemblyUser getBuyer() {
		return buyer;
	}

	/**
	 * @param buyer the buyer to set
	 */
	public void setBuyer(QPaymentsAssemblyUser buyer) {
		this.buyer = buyer;
	}

	/**
	 * @return the seller
	 */
	public QPaymentsAssemblyUser getSeller() {
		return seller;
	}

	/**
	 * @param seller the seller to set
	 */
	public void setSeller(QPaymentsAssemblyUser seller) {
		this.seller = seller;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QPaymentsAssemblyItemResponse [id=" + id + ", description=" + description + ", state=" + state
				+ ", paymentType=" + paymentType + ", amount=" + amount + ", currency=" + currency
				+ ", depositReference=" + depositReference + ", buyer=" + buyer + ", seller=" + seller + "]";
	}
	
	
}
