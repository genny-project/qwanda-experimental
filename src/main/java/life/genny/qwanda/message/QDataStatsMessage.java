package life.genny.qwanda.message;

import java.math.BigDecimal;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

import org.javamoney.moneta.Money;

import com.google.gson.annotations.Expose;

public class QDataStatsMessage extends QDataMessage {

	 static final CurrencyUnit DEFAULT_CURRENCY_AUD = Monetary.getCurrency("AUD");

	 static final long serialVersionUID = 1L;
	@Expose
	 static final String DATATYPE_ANSWER = "Statistics";
	
	@Expose
	Integer buyerCount = 0;
	
	@Expose
	Integer sellerCount = 0;
	
	@Expose
	Integer itemCount = 0;
	
	@Expose
	Integer availableItems = 0;
	
	@Expose
	Money monthTurnover = Money.of(BigDecimal.ZERO,life.genny.qwanda.message.QDataStatsMessage.DEFAULT_CURRENCY_AUD);
	

	 QDataStatsMessage() {
		super(DATATYPE_ANSWER);
	}
	
	public QDataStatsMessage(Integer buyerCount, Integer sellerCount, Integer itemCount, Integer availableItems, Money monthTurnover) {
		super(DATATYPE_ANSWER);
		this.buyerCount = buyerCount;
		this.sellerCount = sellerCount;
		this.itemCount = itemCount;
		this.monthTurnover = monthTurnover;
		this.availableItems = availableItems;
	}

	/**
	 * @return the buyerCount
	 */
	public Integer getBuyerCount() {
		return buyerCount;
	}

	/**
	 * @param buyerCount the buyerCount to set
	 */
	public void setBuyerCount(Integer buyerCount) {
		this.buyerCount = buyerCount;
	}

	/**
	 * @return the sellerCount
	 */
	public Integer getSellerCount() {
		return sellerCount;
	}

	/**
	 * @param sellerCount the sellerCount to set
	 */
	public void setSellerCount(Integer sellerCount) {
		this.sellerCount = sellerCount;
	}

	/**
	 * @return the itemCount
	 */
	public Integer getItemCount() {
		return itemCount;
	}

	/**
	 * @param itemCount the itemCount to set
	 */
	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}

	/**
	 * @return the monthTurnover
	 */
	public Money getMonthTurnover() {
		return monthTurnover;
	}

	/**
	 * @param monthTurnover the monthTurnover to set
	 */
	public void setMonthTurnover(Money monthTurnover) {
		this.monthTurnover = monthTurnover;
	}

	/**
	 * @return the availableItems
	 */
	public Integer getAvailableItems() {
		return availableItems;
	}

	/**
	 * @param availableItems the availableItems to set
	 */
	public void setAvailableItems(Integer availableItems) {
		this.availableItems = availableItems;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QDataStatsMessage [" + (buyerCount != null ? "buyerCount=" + buyerCount + ", " : "")
				+ (sellerCount != null ? "sellerCount=" + sellerCount + ", " : "")
				+ (itemCount != null ? "itemCount=" + itemCount + ", " : "")
				+ (availableItems != null ? "availableItems=" + availableItems + ", " : "")
				+ (monthTurnover != null ? "monthTurnover=" + monthTurnover : "") + "]";
	}

	



	
}
