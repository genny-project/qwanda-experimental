package life.genny.qwanda.payments.assembly;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class QPaymentsAssemblyMetaResponse implements Serializable{

	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;
	
	@Expose
	 int limit;
	
	@Expose
	 int offset;
	
	@Expose
	 int total;

	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * @param limit the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * @param offset the offset to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QPaymentsAssemblyMetaResponse [limit=" + limit + ", offset=" + offset + ", total=" + total + "]";
	}
	
	

}
