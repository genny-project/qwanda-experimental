package life.genny.qwanda.payments;

import java.io.Serializable;

import javax.money.NumberValue;

import com.google.gson.annotations.Expose;

public class QPaymentsFee implements Serializable {

	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;
	
	public enum FEETYPE{
		FIXED(1),
		PERCENTAGE(2),
		PERCENTAGE_WITH_CAP(3),
		PERCENTAGE_WITH_MIN(4);

         final int value;

        FEETYPE(final int newValue) {
            value = newValue;
        }

        public int getValue() { return value; }
    }
	
	public enum PAYMENT_TO {
		buyer,
		seller,
		cc,
		int_wire;
	}
	
	@Expose
	 String id;
	
	@Expose
	 String name;
	
	/* Type of fee */
	@Expose
	 int type;
	
	@Expose
	 Double amount;
	
	@Expose
	 String cap;
	
	@Expose
	 Double min;
	
	@Expose
	 Double max;
	
	@Expose 
	 PAYMENT_TO to;

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
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the cap
	 */
	public String getCap() {
		return cap;
	}

	/**
	 * @param cap the cap to set
	 */
	public void setCap(String cap) {
		this.cap = cap;
	}

	/**
	 * @return the to
	 */
	public PAYMENT_TO getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(PAYMENT_TO to) {
		this.to = to;
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
	 * @return the min
	 */
	public Double getMin() {
		return min;
	}

	/**
	 * @param min the min to set
	 */
	public void setMin(Double min) {
		this.min = min;
	}

	/**
	 * @return the max
	 */
	public Double getMax() {
		return max;
	}

	/**
	 * @param max the max to set
	 */
	public void setMax(Double max) {
		this.max = max;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QPaymentsFee [id=" + id + ", name=" + name + ", type=" + type + ", amount=" + amount + ", cap=" + cap
				+ ", min=" + min + ", max=" + max + ", to=" + to + "]";
	}

	public QPaymentsFee(String name, FEETYPE feeType, Double amount, PAYMENT_TO to) {
		super();
		
		if(name != null && !name.trim().isEmpty()) {
			this.name = name;
		} else {
			throw new IllegalArgumentException("Name of job cannot be empty");
		}
		
		if(feeType.getValue() > 0 && feeType.getValue() <= 4) {
			this.type = feeType.getValue();
		} else {
			throw new IllegalArgumentException("Fee type cannot be invalid. The valid fee types are FIXED, PERCENTAGE, PERCENTAGE_WITH_CAP and PERCENTAGE_WITH_MIN");
		}
		
		/* Fee amount is required only if fee type is FIXED */
		if(feeType.equals(FEETYPE.FIXED)) {
			if(amount.doubleValue() > 0) {
				this.amount = amount;
			} else {
				throw new IllegalArgumentException("Fee amount needs to be greater than 0 cents");
			}
		}
		
		
		if(to != null) {
			this.to = to;
		} else {
			throw new IllegalArgumentException("Payment recipient type cannot be empty");
		}
		
	}
	
}
