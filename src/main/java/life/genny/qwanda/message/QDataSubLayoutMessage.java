package life.genny.qwanda.message;

import com.google.gson.annotations.Expose;

import life.genny.qwanda.Layout;

public class QDataSubLayoutMessage extends QDataMessage {

	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;
	
	
	@Expose
	 Layout[] items;
	
	 static final String DATATYPE_LAYOUT = "SUB_LAYOUT";

	public QDataSubLayoutMessage(final Layout[] layoutItems)
	{
		this(layoutItems, "DUMMY");
	}

	public QDataSubLayoutMessage(final Layout layoutItem, final String token) {
		this(new Layout[] {layoutItem},token);
	}
	public QDataSubLayoutMessage(final Layout[] layoutItems, final String token) {
		super(DATATYPE_LAYOUT);
		this.items = layoutItems;
		setToken(token);
	}

	/**
	 * @return the items
	 */
	public Layout[] getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(Layout[] items) {
		this.items = items;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String itemCodes = "";
			for (Layout layout :items) {
				itemCodes += layout.getName()+",";
			}
		return "QDataSubLayoutMessage "+itemCodes;
	}



}
