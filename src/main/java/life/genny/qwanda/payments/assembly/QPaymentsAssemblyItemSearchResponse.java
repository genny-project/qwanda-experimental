package life.genny.qwanda.payments.assembly;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;

public class QPaymentsAssemblyItemSearchResponse implements Serializable {

	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;
	
	@Expose
	 QPaymentsAssemblyItemResponse items;

	/**
	 * @return the items
	 */
	public QPaymentsAssemblyItemResponse getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(QPaymentsAssemblyItemResponse items) {
		this.items = items;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QPaymentsAssemblyItemSearchResponse [items=" + items + "]";
	}
	

}
