package life.genny.qwanda.payments;

import java.io.Serializable;
import java.util.Arrays;
import java.util.UUID;

import javax.money.CurrencyUnit;
import javax.money.NumberValue;

import com.google.gson.annotations.Expose;

public class QPaymentsItem implements Serializable {

	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;
	
	public enum PaymentTransactionType {
		escrow,
		express,
		escrow_partial_release,
		approve
	}
	
	/* Will be a randomnly generated UUID */
	@Expose
	 String id;
	
	/* name of job */
	@Expose
	 String name;
	
	@Expose
	 String description;
	
	@Expose
	 PaymentTransactionType paymentType;
	
	@Expose
	 Double amount;
	
	@Expose
	 CurrencyUnit currency;

	/* Array of fee Ids */
	@Expose
	 String[] fees;

	@Expose
	 QPaymentsUser buyer;
	
	@Expose
	 QPaymentsUser seller;
	
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the paymentType
	 */
	public PaymentTransactionType getPaymentType() {
		return paymentType;
	}

	/**
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(PaymentTransactionType paymentType) {
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
	public CurrencyUnit getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(CurrencyUnit currency) {
		this.currency = currency;
	}

	/**
	 * @return the fees
	 */
	public String[] getFees() {
		return fees;
	}

	/**
	 * @param fees the fees to set
	 */
	public void setFees(String[] fees) {
		this.fees = fees;
	}
	

	/**
	 * @return the buyer
	 */
	public QPaymentsUser getBuyer() {
		return buyer;
	}

	/**
	 * @param buyer the buyer to set
	 */
	public void setBuyer(QPaymentsUser buyer) {
		this.buyer = buyer;
	}

	/**
	 * @return the seller
	 */
	public QPaymentsUser getSeller() {
		return seller;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QPaymentsItem [id=" + id + ", name=" + name + ", description=" + description + ", paymentType="
				+ paymentType + ", amount=" + amount + ", currency=" + currency + ", fees=" + Arrays.toString(fees)
				+ ", buyer=" + buyer + ", seller=" + seller + "]";
	}

	/**
	 * @param seller the seller to set
	 */
	public void setSeller(QPaymentsUser seller) {
		this.seller = seller;
	}

	public QPaymentsItem( String name, String description, PaymentTransactionType paymentType,
			Double amount, CurrencyUnit currency, String[] fees,
			QPaymentsUser buyer, QPaymentsUser seller) {
		super();
		
		this.id = UUID.randomUUID().toString();
		
		if(name != null && !name.trim().isEmpty() ) {
			this.name = name;
		} else {
			throw new IllegalArgumentException("Title of load cannot be empty");
		}
		
		if(paymentType != null) {
			this.paymentType = paymentType;
		} else {
			throw new IllegalArgumentException("Payment type cannot be empty");
		}
		
		Double limitAmount = 100.00;
		if(amount.doubleValue() >= limitAmount) {
			this.amount = amount;
		} else {
			throw new IllegalArgumentException("Item amount needs to be greater than 100 cents");
		}
		
		if(currency != null && !currency.toString().trim().isEmpty()) {
			this.currency = currency;
		} else {
			throw new IllegalArgumentException("Currency cannot be empty");
		}

		if(fees != null && fees.length > 0) {
			this.fees = fees;
		} /* else {
			throw new IllegalArgumentException("Fees for the item should not be empty");
		} */
		
		if(buyer != null) {
			this.buyer = buyer;
		} else {
			throw new IllegalArgumentException("Buyer assembly user creation has failed");
		}
		
		if(seller != null) {
			this.seller = seller;
		} else {
			throw new IllegalArgumentException("Seller assembly user creation has failed");
		}

		
		/* Not mandatory */
		this.description = description;
	}
	
}
