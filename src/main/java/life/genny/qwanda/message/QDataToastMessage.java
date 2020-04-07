package life.genny.qwanda.message;

import com.google.gson.annotations.Expose;

public class QDataToastMessage extends QDataMessage {
	
	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;

	 static final String MESSAGE_DATATYPE = "CMD_NOTIFICATION";
	
	@Expose
	 String style;
	
	@Expose
	 String message;
	
	

	public QDataToastMessage(final String style, final String message) {
		super(MESSAGE_DATATYPE);
		this.style = style;
		this.message = message;
	}



	/**
	 * @return the style
	 */
	public String getStyle() {
		return style;
	}



	/**
	 * @param style the style to set
	 */
	public void setStyle(String style) {
		this.style = style;
	}



	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}



	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}	
	
	
	
	
	

	
}
