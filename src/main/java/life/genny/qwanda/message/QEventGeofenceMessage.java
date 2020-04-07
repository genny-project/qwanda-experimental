package life.genny.qwanda.message;

import com.google.gson.annotations.Expose;

import life.genny.qwanda.GPS;

public class QEventGeofenceMessage extends QEventMessage {
	  /**
	 * 
	 */
	 static final long serialVersionUID = 1L;
	
	 static final String EVENT_TYPE_EVT_GEOFENCE = "EVT_GEOFENCE";
	@Expose
	   GPS gps;
	@Expose
	   String token;

	public QEventGeofenceMessage(String geofenceCode,GPS gps, String token) {
		super(EVENT_TYPE_EVT_GEOFENCE, geofenceCode);
		this.gps = gps;
		this.token = token;
	}



	/**
	 * @return the gps
	 */
	public GPS getGps() {
		return gps;
	}



	/**
	 * @param gps the gps to set
	 */
	public void setGps(GPS gps) {
		this.gps = gps;
	}



	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "QEventGeofenceMessage ["+this.getData().getCode()+":"+this.getGps()
				+ ", event_type=" + getEvent_type() + ", msg_type=" + getMsg_type() + "]";
	}

	
	
}
