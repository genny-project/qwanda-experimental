package life.genny.qwanda.message;

import com.google.gson.annotations.Expose;

public class QDataKeyValueMessage extends QDataMessage {

	 static final long serialVersionUID = 1L;
	@Expose
	 String json;
	 static final String DATATYPE_KEYVALUE = "KEY_VALUE";

	public QDataKeyValueMessage(final String json) {
		super(DATATYPE_KEYVALUE);
		setJson(json);
	}

	public String getJson() {
		return json;
	}

	public void setJson(final String json) {
		this.json = json;
	}
}
