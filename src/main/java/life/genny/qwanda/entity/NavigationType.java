package life.genny.qwanda.entity;

public enum NavigationType {
	
	ROUTE_BACK("ROUTE_BACK"),
	ROUTE_CHANGE("ROUTE_CHANGE");
	
	String navigationType;
	NavigationType(String type) {
		this.navigationType = type;
	}
	
	public String getNavigationType() {
		return this.navigationType;
	}
}
