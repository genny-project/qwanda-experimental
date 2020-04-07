package life.genny.qwanda.message;

import java.util.Arrays;

import com.google.gson.annotations.Expose;

import life.genny.qwanda.GPS;

public class QDataGPSMessage extends QDataMessage {

	 static final long serialVersionUID = 1L;
	@Expose
	 GPS[] items;
	 static final String DATATYPE_GPS = GPS.class.getSimpleName();

	public QDataGPSMessage(final GPS[] items) {
		super(DATATYPE_GPS);
		setItems(items);
	}

	public GPS[] getItems() {
		return items;
	}

	public void setItems(final GPS[] items) {
		this.items = items;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QDataGPSMessage [" + (items != null ? "items=" + Arrays.toString(items) : "") + "]";
	}
	
	
}
