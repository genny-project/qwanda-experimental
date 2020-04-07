package life.genny.qwanda.message;

import java.util.Map;

import com.google.gson.annotations.Expose;

public class QEventProcess {
	@Expose
	 String businessType;
	@Expose
	 String businessEvent;
	@Expose
	 String container;
	@Expose
	 Long itemId;
	@Expose
	 String processId;
	@Expose
	 Long processInstanceId;
	@Expose
	 Map<String, Object> params;
	
	 QEventProcess() {}
	
	 QEventProcess(String businessType, String businessEvent) {
		this.businessEvent = businessEvent;
		this.businessType = businessType;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getBusinessEvent() {
		return businessEvent;
	}
	public void setBusinessEvent(String businessEvent) {
		this.businessEvent = businessEvent;
	}
	public String getContainer() {
		return container;
	}
	public void setContainer(String container) {
		this.container = container;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public Long getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(Long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "QEventProcess [businessType=" + businessType + ", businessEvent=" + businessEvent + ", container="
				+ container + ", itemId=" + itemId + ", processId=" + processId + ", processInstanceId="
				+ processInstanceId + ", params=" + params + "]";
	}
	
 }
