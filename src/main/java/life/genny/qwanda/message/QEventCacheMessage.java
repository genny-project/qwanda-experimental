package life.genny.qwanda.message;

import com.google.gson.annotations.Expose;

public class QEventCacheMessage extends QEventMessage {
	  /**
	 * 
	 */
	 static final long serialVersionUID = 1L;
	
	 static final String EVENT_TYPE_CACHE_MISS = "EVT_CACHE_MISS";
	@Expose
	   String token;
	@Expose
	   String linkCode;

	public QEventCacheMessage(String sourceBaseEntityCode,String targetBaseEntityCode, String code, String linkCode, String token) {
		super(EVENT_TYPE_CACHE_MISS, code);
		this.token = token;
		this.linkCode = linkCode;
	}



	/**
	 * @return the linkCode
	 */
	public String getLinkCode() {
		return linkCode;
	}



	/**
	 * @param linkCode the linkCode to set
	 */
	public void setLinkCode(String linkCode) {
		this.linkCode = linkCode;
	}



	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "QEventCacheMessage [targetCode="+this.getData().getCode()
				+ ", event_type=" + getEvent_type() + ", msg_type=" + getMsg_type() + "]";
	}

	
	
}
