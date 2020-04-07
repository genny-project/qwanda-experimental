package life.genny.qwanda.message;

import com.google.gson.annotations.Expose;

import life.genny.qwanda.attribute.Attribute;

public class QDataAttributeMessage extends QDataMessage{

	 static final long serialVersionUID = 1L;
	@Expose
	 Attribute[] items;
	 static final String DATATYPE_ATTRIBUTE = Attribute.class.getSimpleName();

	public QDataAttributeMessage(Attribute[] items) {
		super(DATATYPE_ATTRIBUTE);
		setItems(items);
	}

	public Attribute[] getItems() {
		return items;
	}

	public void setItems(Attribute[] items) {
		this.items = items;
	}

}
