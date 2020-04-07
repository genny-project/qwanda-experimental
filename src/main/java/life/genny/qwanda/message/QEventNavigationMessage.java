package life.genny.qwanda.message;

import java.time.LocalDateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import life.genny.qwanda.DateTimeDeserializer;

public class QEventNavigationMessage extends QEventMessage {
	
	 String route;
	 String itemCode;
	
	 static final long serialVersionUID = 1L;

	 static final String EVENT_TYPE_BTN_CLICK = "NAVIGATION_EVENT";

	public QEventNavigationMessage(String route, String itemCode) {
		super(EVENT_TYPE_BTN_CLICK, route);
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
}
