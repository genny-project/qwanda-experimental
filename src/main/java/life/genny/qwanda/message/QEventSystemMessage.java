package life.genny.qwanda.message;

import java.util.Properties;

public class QEventSystemMessage extends QEventMessage {
	  
	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;
	
	 static final String EVENT_TYPE_SYSTEM = "EVT_SYSTEM";

	public QEventSystemMessage(final String systemCode) {
		this(systemCode,new Properties(),null);
	}	
	public QEventSystemMessage(final String systemCode, final Properties properties) {
		this(systemCode,properties,null);
	}	
	
	public QEventSystemMessage(final String systemCode, final Properties properties, String token) {
		super(EVENT_TYPE_SYSTEM, systemCode);
		setToken(token);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QEventSystemMessage []";
	}


	
	
}
